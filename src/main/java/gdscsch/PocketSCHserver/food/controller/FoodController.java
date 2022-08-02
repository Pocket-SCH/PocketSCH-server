package gdscsch.PocketSCHserver.food.controller;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.food.service.FoodService;
import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/food")
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/foodlist/category/{categoryId}")
    public ResponseEntity getFoodListByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<FoodMenu> foodMenus = foodService.getFoodListByCategory(categoryId);

        return foodMenus.size() != 0 ? new ResponseEntity(DefaultRes.res(StatusCode.OK, "음식 목록 반환 완료", foodMenus), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "해당 카테고리 음식 없음", foodMenus), HttpStatus.OK);
    }
}
