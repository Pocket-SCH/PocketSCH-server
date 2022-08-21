package gdscsch.PocketSCHserver.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodRelation is a Querydsl query type for FoodRelation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodRelation extends EntityPathBase<FoodRelation> {

    private static final long serialVersionUID = -1770117567L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodRelation foodRelation = new QFoodRelation("foodRelation");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QFoodMenu foodMenu;

    public final QFoodStore foodStore;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFoodRelation(String variable) {
        this(FoodRelation.class, forVariable(variable), INITS);
    }

    public QFoodRelation(Path<? extends FoodRelation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodRelation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodRelation(PathMetadata metadata, PathInits inits) {
        this(FoodRelation.class, metadata, inits);
    }

    public QFoodRelation(Class<? extends FoodRelation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodMenu = inits.isInitialized("foodMenu") ? new QFoodMenu(forProperty("foodMenu"), inits.get("foodMenu")) : null;
        this.foodStore = inits.isInitialized("foodStore") ? new QFoodStore(forProperty("foodStore")) : null;
    }

}

