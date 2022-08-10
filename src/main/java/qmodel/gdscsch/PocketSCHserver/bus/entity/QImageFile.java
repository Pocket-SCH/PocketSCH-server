package gdscsch.PocketSCHserver.bus.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageFile is a Querydsl query type for ImageFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageFile extends EntityPathBase<ImageFile> {

    private static final long serialVersionUID = -237985850L;

    public static final QImageFile imageFile = new QImageFile("imageFile");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath origFilename = createString("origFilename");

    public final StringPath token = createString("token");

    public QImageFile(String variable) {
        super(ImageFile.class, forVariable(variable));
    }

    public QImageFile(Path<? extends ImageFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageFile(PathMetadata metadata) {
        super(ImageFile.class, metadata);
    }

}

