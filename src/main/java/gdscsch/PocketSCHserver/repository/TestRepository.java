package gdscsch.PocketSCHserver.repository;

import gdscsch.PocketSCHserver.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Optional<Test> findById(Integer id);
}
