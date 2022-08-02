package gdscsch.PocketSCHserver.food.repository;

import gdscsch.PocketSCHserver.bus.entity.Bus;
import gdscsch.PocketSCHserver.food.entity.FoodStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodStoreRepository extends JpaRepository<FoodStore, Integer> {
    FoodStore save(FoodStore foodStore);
}
