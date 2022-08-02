package gdscsch.PocketSCHserver.food.repository;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer> {
    FoodMenu save(FoodMenu foodMenu);

    List<FoodMenu> findByFoodCategoryId(Integer categoryId);
}
