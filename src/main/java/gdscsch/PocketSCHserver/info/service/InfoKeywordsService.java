package gdscsch.PocketSCHserver.info.service;


import gdscsch.PocketSCHserver.info.dto.KeywordDto;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.info.exception.EmptyStringException;
import gdscsch.PocketSCHserver.info.exception.KeywordExistException;
import gdscsch.PocketSCHserver.info.repository.KeywordRepository;
import gdscsch.PocketSCHserver.token.entity.Token;
import gdscsch.PocketSCHserver.token.repository.TokenRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        keyword.setKeyword(keyword.getKeyword().trim());
        Keyword getKeyword = keywordRepository.findAllByTokenAndKeyword(foundToken, keyword.getKeyword());
        if (getKeyword != null) {
            throw new KeywordExistException("이미 존재하는 키워드 입니다.");
        }

        Keyword savedKeyword = keywordRepository.save(new Keyword(foundToken, keyword.getKeyword()));

        return modelMapper.map(savedKeyword, KeywordDto.Get.class);
    }

    public Page<KeywordDto.Get> readKeywords(Integer page, Integer size, String token) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Token foundToken = tokenRepository.findByToken(token).get();
        Page<Keyword> keywordsPage = keywordRepository.findAllByToken(foundToken, pageable);

        List<KeywordDto.Get> keywordDtos = new ArrayList<KeywordDto.Get>();

        for (Keyword keyword : keywordsPage.getContent()) {
            keywordDtos.add(modelMapper.map(keyword, KeywordDto.Get.class));
        }

        return new PageImpl<KeywordDto.Get>(keywordDtos, pageable, keywordsPage.getTotalElements());
    }

    public boolean deleteKeyword(String token, Integer keywordId) {

        Token foundToken = tokenRepository.findByToken(token).get();
        List<Keyword> keywords = keywordRepository.findAllByTokenAndId(foundToken, keywordId);

        for (Keyword keyword : keywords) {
            if (keyword.getId().equals(keywordId)) {
                keywordRepository.deleteById(keywordId);
                return true;
            }
        }
        return false;
    }
}