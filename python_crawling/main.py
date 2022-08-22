import re
import logging
import sys
import datetime

import firebase_admin
import mariadb
import requests
from bs4 import BeautifulSoup
from firebase_admin import credentials, messaging

Log_Format = "%(levelname)s %(asctime)s - %(message)s"

logging.basicConfig(filename="./logfile.log",
                    filemode="a",
                    format=Log_Format,
                    level=logging.INFO)

logger = logging.getLogger()

cred = credentials.Certificate('./service-account.json')
default_app = firebase_admin.initialize_app(cred)


def db_connect() -> mariadb.Connection:
    """ db 연동하기 """
    try:
        conn = mariadb.connect(
            # user="root",
            # password="root",
            # host="127.0.0.1",
            # port=3306,
            # database="pocket_sch"
        )
    except mariadb.Error as e:
        logger.error(f"Error connecting to MariaDB Platform: {e}")
        sys.exit(1)

    return conn


def find_last_number_query(category_id: int) -> str:
    """ 대학공지 또는 학과 공지 마지막 공지번호 찾는 쿼리
        반환 함수

        대학공지 : 0
        학사공지 : 1

    :param category: 공지 카테고리 번호
    :return: 쿼리문
    """
    return (
        "SELECT `id`, `info_number` "
        "FROM `info` "
        f"WHERE `info_category_id` = {category_id} "
        "ORDER BY `id` DESC "
        "LIMIT 1"
    )


def select_tokens_keywords_query() -> str:
    """ 토큰들의 키워드 얻기
    :return:
    """
    return "SELECT keyword, token_token FROM keyword ORDER BY token_token;"


def last_info_number(category_id: int) -> int:
    """ 대학공지 또는 학과 공지 마지막 공지번호 반환

        대학공지 : 0
        학사공지 : 1

    :param category_id:
    :return: 마지막 공지 번호
    """
    conn = db_connect()
    cur = conn.cursor()
    cur.execute(find_last_number_query(category_id=category_id))

    last_number = 0
    for id, info_numb in cur:
        last_number = info_numb

    cur.close()

    return last_number


def get_keywords() -> dict[str: list[str, ...]]:
    """ 토큰들의 키워드 얻기 """

    tokens_keywords = {}

    conn = db_connect()
    cur = conn.cursor()
    cur.execute(select_tokens_keywords_query())

    for keyword, token in cur:
        if token not in tokens_keywords:
            tokens_keywords[token] = [keyword]
            continue
        tokens_keywords[token].append(keyword)
    cur.close()
    return tokens_keywords


def insert_data(sql: str) -> int:
    """ 공지사항 정보 삽입

    :param sql: 쿼리
    :return: int
    """
    logger.info(f"insert query > {sql}")
    conn = db_connect()
    cur = conn.cursor()
    cur.execute(sql)
    conn.commit()
    cur.close()


def crawling(check_number1: int, check_number2: int):
    """ 크롤링 함수 """
    boards = ["010100", "010200"]  # < 공지 번호
    checks = [check_number1, check_number2]
    res1 = {}  # 크롤링 결과 저장; {key="번호":value=["제목","링크","일자"]}
    res2 = {}

    logging.info(f"crawling last number ({check_number1}, {check_number2})")
    for i in range(2):
        url = "https://home.sch.ac.kr/sch/06/" + boards[i] + ".jsp?"

        response = requests.get(url)

        if response.status_code == 200:
            html = response.text
            soup = BeautifulSoup(html, 'html.parser')
        else:
            print(response.status_code)

        filter_data = soup.select('div.board_list a[href]')  # CSS selector
        filter_num = soup.select('div.board_list td.seq')

        for f, n in zip(filter_data, filter_num):
            try:
                num = n.get_text().strip()
                if int(num) > int(checks[i]):
                    articel_no = re.search('article_no=(.+?)&', str(f)).group(1)
                    title = re.search('\n(.+?)\n', str(f).replace("  ", "")).group(1)
                    title = title.replace("'", "").replace('"', "")
                    link = "https://home.sch.ac.kr/sch/06/" + boards[i] + ".jsp?mode=view&article_no=" + articel_no
                    if (i == 0):
                        res1[num] = [title, link, articel_no[0:8]]
                    else:
                        res2[num] = [title, link, articel_no[0:8]]
            except:
                continue

    return {
        "대학공지": res1,
        "학사공지": res2
    }


def crawling_data_insert_query(data: dict = None) -> str:
    """ 크롤링데이터 insert 쿼리로 변경 """

    insert_data_row = []
    for category, data in data.items():
        for notice_number, row in data.items():
            title = row[0]
            url = row[1]
            notice_date = datetime.date(int(row[2][:4]), int(row[2][4:6]), int(row[2][6:8])).strftime("%Y-%m-%d")
            now = datetime.datetime.now()

            if category == "대학공지":
                category_id = 0
            elif category == "학사공지":
                category_id = 1
            else:
                pass

            insert_data_row.append((
                f"('{now}', '{notice_date} 00:00:00.000000', {notice_number}, '{title}', '{url}', {category_id})"
            ))

    logger.info(f"Insert data count {len(insert_data_row)}")
    insert_data_row.reverse()
    values = ', \n'.join(insert_data_row)

    if values:
        return (f"INSERT INTO `info` (`created_at`, `info_date`, `info_number`, `title`, `url`, `info_category_id`) "
                f"VALUES {values};")
    return ""


def _fcm_send(registration_token: str, title: str, body: str) -> None:
    """ FCM 알림 전송 함수

    :param registration_token: 클라이언트 토큰
    :param title: 알림 제목
    :param body: 알림 내용
    """

    message = messaging.Message(
        android=messaging.AndroidConfig(
            ttl=datetime.timedelta(seconds=3600),
            priority='normal',
            notification=messaging.AndroidNotification(
                title=title,
                body=body
            ),
        ),
        token=registration_token
    )
    response = messaging.send(message)


def send_all_fcm_message(crawling_data: dict):
    """ 모든 키워드 확인 후 전송하기

    :param crawling_data:
    :return:
    """

    keywords = get_keywords()

    for notice_category, data in crawling_data.items():
        for info_number, info_data in data.items():
            title = info_data[0]
            url = info_data[1]
            date = datetime.date(int(info_data[2][:4]), int(info_data[2][4:6]), int(info_data[2][6:8])).strftime(
                "%Y-%m-%d")

            for token, keywords_list in keywords.items():
                for keyword in keywords_list:
                    if keyword in title:
                        try:  # 전송 부분
                            _fcm_send(registration_token=token, title=f"[{notice_category}]", body=title)
                            logger.info(f'SEND FCM: "{token}" > title : {title}')

                        except:  # 토큰 에러!
                            logger.error(f'Token Error: "{token}"')
                            pass
                        break


if __name__ == '__main__':
    crawling_data = crawling(
        check_number1=last_info_number(category_id=0),
        check_number2=last_info_number(category_id=1)
    )

    insert_sql = crawling_data_insert_query(crawling_data)
    if insert_sql:
        insert_data(insert_sql)
        send_all_fcm_message(crawling_data)

    # print(crawling_data)
    # print(select_keywords_sql())
