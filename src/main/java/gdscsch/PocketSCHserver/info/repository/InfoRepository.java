package gdscsch.PocketSCHserver.info.repository;

import gdscsch.PocketSCHserver.info.entity.Info;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    Info save(Info info);

    List<Info> findAllByInfoCategoryId(Integer id, Pageable pageable);
}