package gdscsch.PocketSCHserver.info.controller;


import gdscsch.PocketSCHserver.info.dto.InfoDto;
import gdscsch.PocketSCHserver.info.dto.KeywordDto;

import gdscsch.PocketSCHserver.info.exception.EmptyStringException;
import gdscsch.PocketSCHserver.info.response.InfoResponseHandler;
import gdscsch.PocketSCHserver.info.response.KeywordResponseHandler;
import gdscsch.PocketSCHserver.info.response.ResponseHandler;
import gdscsch.PocketSCHserver.info.service.InfoKeywordsService;
import gdscsch.PocketSCHserver.info.service.InfoUniversityNoticesService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
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
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/keywords">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/keywords
     * </a>
     */
    @GetMapping("/keywords")
    public ResponseEntity readKeywords(
        @RequestHeader("Authorization") String token
    ) {
        try {
            List<KeywordDto.Get> keywordDtos = infoKeywordsService.readKeywords(token);
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
        @PathVariable(value = "id", required = true) Integer id
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

    private final InfoUniversityNoticesService infoUniversityNoticesService;

    /**
     * 공지사항 (대학공지) 리스트 조회
     * <p>상세 설명 : 대학 공지 리스트 조회
     *
     * @author TaeGyu-Han
     * @see <a href="http://127.0.0.1:8080/pocket-sch/v1/info/university-notices">
     * URL : http://127.0.0.1:8080/pocket-sch/v1/info/university-notices
     * </a>
     */
    @GetMapping(value = "/university-notices")
    public ResponseEntity readUniversityNotices(
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        List<InfoDto.Get> infoDtos = infoUniversityNoticesService.readUniversityNotices(page, size);
        return InfoResponseHandler.infoResponse("university notices 조회 Success", infoDtos, 0, HttpStatus.OK);
    }

    // 공지사항 (학사공지) 리스트 조회

    // 공지사항 (키워드) 리스트 조회
}