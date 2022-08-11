package gdscsch.PocketSCHserver.bus.dto;

import com.querydsl.core.types.dsl.NumberExpression;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BusTimeDto {
    private Integer id;
    private Integer type;   // 0:학내순환   1:직행버스   2:방학버스
    private LocalDateTime busTime;
    private Integer busWeekDay;     // 0:sun 1:mon 2:tue 3:wed ~

    @Builder
    public BusTimeDto(Integer id, Integer type, LocalDateTime busTime, Integer busWeekDay) {
        this.id = id;
        this.type = type;
        this.busTime = busTime;
        this.busWeekDay = busWeekDay;
    }
}
