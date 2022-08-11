package gdscsch.PocketSCHserver.bus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer type;   // 0:학내순환   1:직행버스

    @Column(nullable = false)
    private LocalDateTime busTime;

    @Column(nullable = false)
    private Integer busWeekDay;     // 0:sun 1:mon 2:tue 3:wed ~

    @JsonIgnore
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Bus(Integer type, LocalDateTime busTime, Integer busWeekDay) {
        this.type = type;
        this.busTime = busTime;
        this.busWeekDay = busWeekDay;
    }
}
