package gdscsch.PocketSCHserver.bus.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBus is a Querydsl query type for Bus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBus extends EntityPathBase<Bus> {

    private static final long serialVersionUID = -763635697L;

    public static final QBus bus = new QBus("bus");

    public final DateTimePath<java.time.LocalDateTime> busTime = createDateTime("busTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> busWeekDay = createNumber("busWeekDay", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QBus(String variable) {
        super(Bus.class, forVariable(variable));
    }

    public QBus(Path<? extends Bus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBus(PathMetadata metadata) {
        super(Bus.class, metadata);
    }

}

