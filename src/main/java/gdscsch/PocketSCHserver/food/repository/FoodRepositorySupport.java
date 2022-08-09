package gdscsch.PocketSCHserver.food.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gdscsch.PocketSCHserver.food.entity.FoodRelation;
import gdscsch.PocketSCHserver.food.entity.FoodStore;
import gdscsch.PocketSCHserver.food.entity.QFoodRelation;
import gdscsch.PocketSCHserver.food.entity.QFoodStore;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FoodRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public FoodRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(FoodRelation.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<FoodStore> findAllFoodStoreByFoodMenuId(Integer foodMenuId) {
        QFoodRelation fr = QFoodRelation.foodRelation;
        QFoodStore fs = QFoodStore.foodStore;

        return jpaQueryFactory.select(fr.foodStore)
                .from(fr)
                .where(fr.foodMenu.id.eq(foodMenuId))
                .fetch();
    }
}
