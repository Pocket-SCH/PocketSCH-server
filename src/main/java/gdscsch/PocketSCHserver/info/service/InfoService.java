package gdscsch.PocketSCHserver.info.service;

import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.info.repository.KeywordRepository;
import gdscsch.PocketSCHserver.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import gdscsch.PocketSCHserver.token.entity.Token;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InfoService {
    private final TokenRepository tokenRepository;
    private final KeywordRepository keywordRepository;

    public Keyword addKeyword (String token, String keywordStr) {
        Token currToken = tokenRepository.findByToken(token).get();

        Keyword keyword = keywordRepository.save(new Keyword(currToken, keywordStr));

        return keyword;
    }
}
