package gdscsch.PocketSCHserver.info.service;

import gdscsch.PocketSCHserver.info.dto.KeywordDto;
import gdscsch.PocketSCHserver.info.dto.KeywordDto.Get;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.info.exception.EmptyStringException;
import gdscsch.PocketSCHserver.info.repository.KeywordRepository;
import gdscsch.PocketSCHserver.token.entity.Token;
import gdscsch.PocketSCHserver.token.repository.TokenRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfoKeywordsService {


    private final TokenRepository tokenRepository;
    private final KeywordRepository keywordRepository;

    @Autowired
    private ModelMapper modelMapper;

    public KeywordDto.Get createKeyword(String token, KeywordDto.Craet keyword) {

        if (keyword.getKeyword().equals("")) {
            throw new EmptyStringException("{keyword: \"\"} > keyword를 빈 문자열로  받았습니다.");
        }

        Token foundToken = tokenRepository.findByToken(token).get();
        Keyword savedKeyword = keywordRepository.save(new Keyword(foundToken, keyword.getKeyword()));

        return modelMapper.map(savedKeyword, KeywordDto.Get.class);
    }

    public List<Get> readKeywords(String token) {

        Token foundToken = tokenRepository.findByToken(token).get();
        List<Keyword> keywords = keywordRepository.findAllByToken(foundToken);
        List<KeywordDto.Get> keywordDtos = new ArrayList<Get>();

        for (Keyword keyword : keywords) {
            keywordDtos.add(modelMapper.map(keyword, KeywordDto.Get.class));
        }

        return keywordDtos;
    }

    public boolean deleteKeyword(String token, Integer keywordId) {

        Token foundToken = tokenRepository.findByToken(token).get();
        List<Keyword> keywords = keywordRepository.findAllByToken(foundToken);

        for (Keyword keyword : keywords) {
            if (keyword.getId().equals(keywordId)) {
                keywordRepository.deleteById(keywordId);
                return true;
            }
        }
        return false;
    }
}