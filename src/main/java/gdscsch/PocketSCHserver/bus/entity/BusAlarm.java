package gdscsch.PocketSCHserver.bus.entity;

import gdscsch.PocketSCHserver.token.entity.Token;
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
public class BusAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Token token;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Bus bus;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public BusAlarm(Token token, Bus bus) {
        this.token = token;
        this.bus = bus;
    }
}
