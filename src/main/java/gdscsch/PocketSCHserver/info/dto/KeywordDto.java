package gdscsch.PocketSCHserver.info.dto;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class KeywordDto {

        @Getter
        public static class Craet {
            private String keyword;
        }

        @Getter
        public static class Delete {
            private Integer id;
        }

        @Setter
        @Getter
        @NoArgsConstructor
        public static class Get {
            private Integer id;
            private String keyword;
            private LocalDateTime createdAt;
        }
}