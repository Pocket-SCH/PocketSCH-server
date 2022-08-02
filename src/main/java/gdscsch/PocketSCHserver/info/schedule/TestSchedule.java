package gdscsch.PocketSCHserver.info.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestSchedule {

    // 자정마다 실행
    @Scheduled(cron = "0 0 0 * * *")
    public void TestScheduleAction() {

    }
}
