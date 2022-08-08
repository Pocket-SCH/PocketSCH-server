package gdscsch.PocketSCHserver.info.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InfoDto {

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Get {
        private Integer id;
        private Integer infoNumber;
        private String title;
        private String url;
        private LocalDateTime infoDate;
    }
}