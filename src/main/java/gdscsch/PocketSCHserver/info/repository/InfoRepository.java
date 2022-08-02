package gdscsch.PocketSCHserver.info.repository;

import gdscsch.PocketSCHserver.info.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    Info save(Info info);
}
