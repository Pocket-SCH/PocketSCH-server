package gdscsch.PocketSCHserver.info.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInfoCategory is a Querydsl query type for InfoCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInfoCategory extends EntityPathBase<InfoCategory> {

    private static final long serialVersionUID = 2127233507L;

    public static final QInfoCategory infoCategory = new QInfoCategory("infoCategory");

    public final StringPath category = createString("category");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QInfoCategory(String variable) {
        super(InfoCategory.class, forVariable(variable));
    }

    public QInfoCategory(Path<? extends InfoCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInfoCategory(PathMetadata metadata) {
        super(InfoCategory.class, metadata);
    }

}

