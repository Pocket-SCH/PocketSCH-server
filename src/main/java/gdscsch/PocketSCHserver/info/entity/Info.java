package gdscsch.PocketSCHserver.info.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private InfoCategory infoCategory;

    @Column(nullable = false)
    private Integer infoNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, length = 191)
    private String url;

    @Column(nullable = false)
    private LocalDateTime infoDate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Info(InfoCategory infoCategory, Integer infoNumber, String title, String url, LocalDateTime infoDate) {
        this.infoCategory = infoCategory;
        this.infoNumber = infoNumber;
        this.title = title;
        this.url = url;
        this.infoDate = infoDate;
    }
}
