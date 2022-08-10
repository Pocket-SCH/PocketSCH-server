package gdscsch.PocketSCHserver.bus.repository;

import gdscsch.PocketSCHserver.bus.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import gdscsch.PocketSCHserver.token.entity.*;

public interface ImageFileRepository extends JpaRepository<ImageFile, Integer> {
}