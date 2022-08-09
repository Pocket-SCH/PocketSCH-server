package gdscsch.PocketSCHserver.bus.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import gdscsch.PocketSCHserver.token.entity.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Token token;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public ImageFile(Token token, String origFilename, String fileName, String filePath) {
        this.token = token;
        this.origFilename = origFilename;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
