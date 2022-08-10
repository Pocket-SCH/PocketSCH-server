package gdscsch.PocketSCHserver.test.controller;


import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.test.dto.TestDto;
import gdscsch.PocketSCHserver.response.StatusCode;
import gdscsch.PocketSCHserver.test.servcie.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;
    private gdscsch.PocketSCHserver.response.DefaultRes<Object> DefaultRes;

    @GetMapping("/test")
    public ResponseEntity test() {
        TestDto res = testService.testServiceMethod("hello");

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, res.getTestStr()), HttpStatus.OK);
    }
}