package gdscsch.PocketSCHserver.bus.repository;

import gdscsch.PocketSCHserver.bus.entity.Bus;
import gdscsch.PocketSCHserver.bus.entity.BusAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusAlarmRepository extends JpaRepository<BusAlarm, Integer> {
    BusAlarm save(BusAlarm busAlarm);
}
