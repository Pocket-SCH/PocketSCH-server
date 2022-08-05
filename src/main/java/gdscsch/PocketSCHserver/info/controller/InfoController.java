package gdscsch.PocketSCHserver.info.controller;


import gdscsch.PocketSCHserver.info.dto.InfoDto;
import gdscsch.PocketSCHserver.info.dto.KeywordDto;
import gdscsch.PocketSCHserver.info.exception.EmptyStringException;
import gdscsch.PocketSCHserver.info.response.InfoResponseHandler;
import gdscsch.PocketSCHserver.info.response.KeywordResponseHandler;
import gdscsch.PocketSCHserver.info.response.ResponseHandler;
import gdscsch.PocketSCHserver.info.service.InfoNoticesService;
import gdscsch.PocketSCHserver.info.service.InfoKeywordsService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/info")
public class InfoController {

    private final InfoKeywordsService infoKeywordsService;

    /**
     * 공지 키워드 생성
     * <p>상세 설명 : 공지 목록에서 자신에게 등록할 키워드 생성
     *
     * @param token            : FCM 토큰
     * @param createKeywordDto : 추가 키워드
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/keywords">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/keywords
     * </a>
     */
    @PostMapping("/keywords")
    public ResponseEntity createKeyword(
        @RequestHeader("Authorization") String token,
        @RequestBody KeywordDto.Craet createKeywordDto
    ) {
        try {
            KeywordDto.Get keywordDto = infoKeywordsService.createKeyword(token, createKeywordDto);
            return ResponseHandler.generateResponse("keyword 생성 Success", keywordDto, HttpStatus.CREATED);
        } catch (NoSuchElementException nsee) {
            return ResponseHandler.toekenBadRequestResponse(token, HttpStatus.BAD_REQUEST);
        } catch (EmptyStringException ese) {
            return KeywordResponseHandler.keywordBadRequestResponse(createKeywordDto, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 공지 키워드 리스트 조회
     * <p>상세 설명 : 공지 목록에서 자신에게 등록한 키워드들 조회
     *
     * @param token : FCM 토큰
     * @param page  : 페이지 번호
     * @param size  : 페이지 데이터 호출 사이즈
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/keywords">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/keywords
     * </a>
     */
    @GetMapping("/keywords")
    public ResponseEntity readKeywords(
        @RequestHeader("Authorization") String token,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            Page<KeywordDto.Get> keywordDtos = infoKeywordsService.readKeywords(page, size, token);
            return ResponseHandler.generateResponse("keywords 조회 Success", keywordDtos, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return ResponseHandler.toekenBadRequestResponse(token, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 공지 키워드 삭제
     * <p>상세 설명 : 공지 목록에서 자신이 등록한 키워드 삭제
     *
     * @param token : FCM 토큰
     * @param id
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/keywords/{id}">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/keywords/{id}
     * </a>
     */
    @DeleteMapping("/keywords/{id}")
    public ResponseEntity deleteKeyword(
        @RequestHeader("Authorization") String token,
        @PathVariable(value = "id") Integer id
    ) {
        try {
            boolean successCheck = infoKeywordsService.deleteKeyword(token, id);
            if (successCheck) {
                return KeywordResponseHandler.deleteSuccessResponse("keyword 삭제 Success", id, HttpStatus.OK);
            }
            return KeywordResponseHandler.nonExistIdRequestResponse(id, HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException nsee) {
            return ResponseHandler.toekenBadRequestResponse(token, HttpStatus.BAD_REQUEST);
        }
    }

    private final InfoNoticesService infoNoticesService;

    /**
     * 공지사항 (대학공지) 리스트 조회
     * <p>상세 설명 : 대학 공지 리스트 조회 API 입니다. 파라미터를 통해서
     * 페이지 내비게이션과 페이지 사이즈를 조정할 수 있습니다.
     *
     * @param page : 페이지 번호
     * @param size : 페이지 데이터 호출 사이즈
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/university-notices">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/university-notices
     * </a>
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/university-notices?page=0&size=10">
     * Use param URL : http://127.0.0.1:8080/pocket-sch/v1/info/university-notices?page=0&size=10
     * </a>
     */
    @GetMapping(value = "/university-notices")
    public ResponseEntity readUniversityNotices(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Integer infoCategoryId = 0;
        Page<InfoDto.Get> infoDtos = infoNoticesService.readNotices(page, size, infoCategoryId);
        return InfoResponseHandler.infoResponse("university notices 조회 Success", infoDtos, infoCategoryId, HttpStatus.OK);
    }

    /**
     * 공지사항 (학사공지) 리스트 조회
     * <p>상세 설명 : 학사 공지 리스트 조회 API 입니다. 파라미터를 통해서
     * 페이지 내비게이션과 페이지 사이즈를 조정할 수 있습니다.
     *
     * @param page : 페이지 번호
     * @param size : 페이지 데이터 호출 사이즈
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices
     * </a>
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices?page=0&size=10">
     * Use param URL : http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices?page=0&size=10
     * </a>
     */
    @GetMapping(value = "/bachelor-notices")
    public ResponseEntity readBachelorNotices(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Integer infoCategoryId = 1;
        Page<InfoDto.Get> infoDtos = infoNoticesService.readNotices(page, size, infoCategoryId);
        return InfoResponseHandler.infoResponse("bachelor notices 조회 Success", infoDtos, infoCategoryId, HttpStatus.OK);
    }

    /**
     * 공지사항 (키워드) 리스트 조회
     * <p>상세 설명 : (학사, 대학)공지 리스트 중에서 자신이 입력한 키워드를 반환하는 API입니다.
     * 페이지 내비게이션과 페이지 사이즈를 조정할 수 있습니다.
     *
     * @param token : FCM 토큰
     * @param page : 페이지 번호
     * @param size : 페이지 데이터 호출 사이즈
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices
     * </a>
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices?page=0&size=10">
     * Use param URL : http://127.0.0.1:8080/pocket-sch/v1/info/bachelor-notices?page=0&size=10
     * </a>
     */
    @GetMapping(value = "/search-keyword")
    public ResponseEntity readKeywordsSearch(
        @RequestHeader("Authorization") String token,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            Page<InfoDto.Get> infoDtos = infoNoticesService.readKeywordsNotices(page, size, token);
            return InfoResponseHandler.infoResponse("bachelor notices 조회 Success", infoDtos, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return ResponseHandler.toekenBadRequestResponse(token, HttpStatus.BAD_REQUEST);
        }
    }
}