package gdscsch.PocketSCHserver.food.service;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.food.entity.FoodStore;
import gdscsch.PocketSCHserver.food.repository.FoodMenuRepository;
import gdscsch.PocketSCHserver.food.repository.FoodRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class FoodService {
    private final FoodMenuRepository foodMenuRepository;
    private final FoodRepositorySupport foodRepositorySupport;

    public List<FoodMenu> getFoodMenuListByCategory(Integer categoryId) {
        List<FoodMenu> foodMenus = foodMenuRepository.findByFoodCategoryId(categoryId);

        return foodMenus;
    }

    public List<FoodStore> getFoodStoreListByFoodMenu(Integer foodMenuId) {
        List<FoodStore> foodStores = foodRepositorySupport.findAllFoodStoreByFoodMenuId(foodMenuId);

        return foodStores;
    }
}
