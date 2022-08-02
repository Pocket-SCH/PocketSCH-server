package gdscsch.PocketSCHserver.token.repository;

import gdscsch.PocketSCHserver.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);

    Optional<Token> findByToken(String token);
}
