package gdscsch.PocketSCHserver.info.Repository;

import gdscsch.PocketSCHserver.bus.entity.Bus;
import gdscsch.PocketSCHserver.info.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    Info save(Info info);
}
