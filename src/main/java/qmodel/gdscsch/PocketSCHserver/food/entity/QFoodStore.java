package gdscsch.PocketSCHserver.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodStore is a Querydsl query type for FoodStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodStore extends EntityPathBase<FoodStore> {

    private static final long serialVersionUID = -687818052L;

    public static final QFoodStore foodStore = new QFoodStore("foodStore");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath storeName = createString("storeName");

    public final StringPath storeUrl = createString("storeUrl");

    public QFoodStore(String variable) {
        super(FoodStore.class, forVariable(variable));
    }

    public QFoodStore(Path<? extends FoodStore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodStore(PathMetadata metadata) {
        super(FoodStore.class, metadata);
    }

}

