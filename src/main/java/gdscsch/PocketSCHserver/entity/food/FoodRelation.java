package gdscsch.PocketSCHserver.entity.food;

import gdscsch.PocketSCHserver.entity.bus.Bus;
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
public class FoodRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FoodStore foodStore;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FoodMenu foodMenu;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public FoodRelation(FoodStore foodStore, FoodMenu foodMenu) {
        this.foodStore = foodStore;
        this.foodMenu = foodMenu;
    }
}
