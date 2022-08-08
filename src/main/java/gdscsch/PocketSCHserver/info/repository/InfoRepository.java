package gdscsch.PocketSCHserver.info.repository;


import gdscsch.PocketSCHserver.info.entity.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {

    Info save(Info info);

    Page<Info> findAllByInfoCategoryId(Integer id, Pageable pageable);
}