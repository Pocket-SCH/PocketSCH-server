package gdscsch.PocketSCHserver.bus.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageFileDto {
    private String tokenStr;
    private String origFilename;
    private String fileName;
    private String filePath;


    @Builder
    public ImageFileDto(String tokenStr, String origFilename, String fileName, String filePath) {
        this.tokenStr = tokenStr;
        this.origFilename = origFilename;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
