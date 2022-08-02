package gdscsch.PocketSCHserver.test.repository;

import gdscsch.PocketSCHserver.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Optional<Test> findById(Integer id);
}
