package gdscsch.PocketSCHserver.bus.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gdscsch.PocketSCHserver.bus.dto.BusTimeDto;
import gdscsch.PocketSCHserver.bus.entity.QBus;
import gdscsch.PocketSCHserver.food.entity.FoodRelation;
import gdscsch.PocketSCHserver.food.entity.FoodStore;
import gdscsch.PocketSCHserver.food.entity.QFoodRelation;
import gdscsch.PocketSCHserver.food.entity.QFoodStore;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BusRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(FoodRelation.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BusTimeDto> findAllSubwayTimeByWeekDay(Integer type, Integer weekDay) {
        QBus b = QBus.bus;

        return jpaQueryFactory.select(Projections.fields(BusTimeDto.class, b.id, b.type, b.busTime, b.busWeekDay))
                .from(b)
                .where(b.busWeekDay.eq(weekDay).and(b.type.eq(type)))
                .fetch();
    }
}
