package gdscsch.PocketSCHserver.token.controller;

import gdscsch.PocketSCHserver.token.entity.Token;
import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.response.StatusCode;
import gdscsch.PocketSCHserver.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity registerToken(@RequestHeader("Authorization") String token) {
        Token token1 = tokenService.registerToken(token);

        return token1 != null ? new ResponseEntity(DefaultRes.res(StatusCode.OK, "토큰 등록 완료", token1.getToken()),
            HttpStatus.OK) :
            new ResponseEntity(DefaultRes.res(StatusCode.OK, "토큰 등록 실패", token1.getToken()), HttpStatus.OK);
    }