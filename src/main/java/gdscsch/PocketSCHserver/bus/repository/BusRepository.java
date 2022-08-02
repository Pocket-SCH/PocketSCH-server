package gdscsch.PocketSCHserver.bus.repository;

import gdscsch.PocketSCHserver.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus save(Bus bus);
}
