package gdscsch.PocketSCHserver.info.response;

import gdscsch.PocketSCHserver.info.dto.InfoDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InfoResponseHandler {

    public static ResponseEntity<Object> infoResponse(String message, List<InfoDto.Get> infoDtos, Integer infoCategoryId,
        HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("message", message);
        map.put("statusCode", status.value());
        map.put("infoCategory", infoCategoryId);
        map.put("data", infoDtos);

        return new ResponseEntity<Object>(map, status);
    }
}