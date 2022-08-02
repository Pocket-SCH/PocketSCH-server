package gdscsch.PocketSCHserver.food.service;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.food.repository.FoodMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class FoodService {
    private final FoodMenuRepository foodMenuRepository;

    public List<FoodMenu> getFoodListByCategory(Integer categoryId) {
        List<FoodMenu> foodMenus = foodMenuRepository.findByFoodCategoryId(categoryId);

        return foodMenus;
    }
}
