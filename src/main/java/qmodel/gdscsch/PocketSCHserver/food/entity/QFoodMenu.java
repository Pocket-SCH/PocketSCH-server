package gdscsch.PocketSCHserver.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodMenu is a Querydsl query type for FoodMenu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodMenu extends EntityPathBase<FoodMenu> {

    private static final long serialVersionUID = -715117532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodMenu foodMenu = new QFoodMenu("foodMenu");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QFoodCategory foodCategory;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QFoodMenu(String variable) {
        this(FoodMenu.class, forVariable(variable), INITS);
    }

    public QFoodMenu(Path<? extends FoodMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodMenu(PathMetadata metadata, PathInits inits) {
        this(FoodMenu.class, metadata, inits);
    }

    public QFoodMenu(Class<? extends FoodMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new QFoodCategory(forProperty("foodCategory")) : null;
    }

}

