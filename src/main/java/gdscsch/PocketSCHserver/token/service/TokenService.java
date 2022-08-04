package gdscsch.PocketSCHserver.token.service;

import gdscsch.PocketSCHserver.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import gdscsch.PocketSCHserver.token.entity.Token;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token registerToken(String token) {
        if (tokenRepository.findByToken(token).isPresent()) {
            return tokenRepository.findByToken(token).get();
        } else {
            return tokenRepository.save(new Token(token));
        }
    }
}
