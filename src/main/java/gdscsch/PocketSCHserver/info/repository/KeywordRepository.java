package gdscsch.PocketSCHserver.info.repository;

import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.token.entity.Token;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    Keyword save(Keyword keyword);

    List<Keyword> findAllByToken(Token token);

    void deleteById(Integer id);
}