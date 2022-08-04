package gdscsch.PocketSCHserver.info.response;

import gdscsch.PocketSCHserver.info.dto.KeywordDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class KeywordResponseHandler {

    public static ResponseEntity<Object> deleteSuccessResponse(String message, Integer keywordId, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("deletedId", keywordId);

        map.put("message", message);
        map.put("statusCode", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> nonExistIdRequestResponse(Integer keywordId, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", keywordId);

        map.put("message", "해당 토큰에 입력한 Keyword id가 존재하지 않는 Error");
        map.put("statusCode", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> keywordBadRequestResponse(KeywordDto.Craet keyword, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("keyword", keyword.getKeyword());

        map.put("message", "빈 keyword 입력 Error");
        map.put("statusCode", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map, status);
    }
}
