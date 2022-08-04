package gdscsch.PocketSCHserver.info.response;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, Object responseObj, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("statusCode", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> toekenBadRequestResponse(String token, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("Authorization", token);

        map.put("message", "존재하지 않는 Token Error");
        map.put("statusCode", status.value());
        map.put("data", data);

        return new ResponseEntity<Object>(map, status);
    }
}