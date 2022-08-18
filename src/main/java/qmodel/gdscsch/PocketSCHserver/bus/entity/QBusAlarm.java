package gdscsch.PocketSCHserver.bus.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusAlarm is a Querydsl query type for BusAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusAlarm extends EntityPathBase<BusAlarm> {

    private static final long serialVersionUID = 915039106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusAlarm busAlarm = new QBusAlarm("busAlarm");

    public final QBus bus;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final gdscsch.PocketSCHserver.token.entity.QToken token;

    public QBusAlarm(String variable) {
        this(BusAlarm.class, forVariable(variable), INITS);
    }

    public QBusAlarm(Path<? extends BusAlarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusAlarm(PathMetadata metadata, PathInits inits) {
        this(BusAlarm.class, metadata, inits);
    }

    public QBusAlarm(Class<? extends BusAlarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bus = inits.isInitialized("bus") ? new QBus(forProperty("bus")) : null;
        this.token = inits.isInitialized("token") ? new gdscsch.PocketSCHserver.token.entity.QToken(forProperty("token")) : null;
    }

}

