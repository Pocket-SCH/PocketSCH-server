package gdscsch.PocketSCHserver.info.controller;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.food.service.FoodService;
import gdscsch.PocketSCHserver.info.dto.AddKeywordDto;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.info.service.InfoService;
import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/info")
public class InfoController {
    private final InfoService infoService;

    @PostMapping("/keyword/add")
    public ResponseEntity addKeyword(@RequestHeader("Authorization") String token, @RequestBody AddKeywordDto addKeywordDto) {
        Keyword keyword = infoService.addKeyword(token, addKeywordDto.getKeyword());

        return keyword != null ? new ResponseEntity(DefaultRes.res(StatusCode.OK, "키워드 추가 완료", keyword.getId()), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "키워드 추가 실패"), HttpStatus.OK);
    }
}