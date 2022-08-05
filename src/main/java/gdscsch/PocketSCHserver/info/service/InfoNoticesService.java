package gdscsch.PocketSCHserver.info.service;


import gdscsch.PocketSCHserver.info.dto.InfoDto;
import gdscsch.PocketSCHserver.info.entity.Info;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import gdscsch.PocketSCHserver.info.repository.InfoRepository;
import gdscsch.PocketSCHserver.info.repository.InfoRepositoryCustomImpl;
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
public class InfoNoticesService {

    private final InfoRepository infoRepository;
    private final InfoRepositoryCustomImpl infoRepositoryCustomImpl;
    private final KeywordRepository keywordRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<InfoDto.Get> readNotices(Integer page, Integer size, Integer infoCategoryId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Info> infoNoticesPage = infoRepository.findAllByInfoCategoryId(infoCategoryId, pageable);

        List<InfoDto.Get> infoDtos = new ArrayList<InfoDto.Get>();
        for (Info infoNotice : infoNoticesPage.getContent()) {
            infoDtos.add(modelMapper.map(infoNotice, InfoDto.Get.class));
        }

        Page<InfoDto.Get> infoDtoPage = new PageImpl<InfoDto.Get>(infoDtos, pageable, infoNoticesPage.getTotalElements());

        return infoDtoPage;
    }

    public Page<InfoDto.Get> readKeywordsNotices(Integer page, Integer size, String token) {

        Token foundToken = tokenRepository.findByToken(token).get();
        List<Keyword> keywords = keywordRepository.findAllByToken(foundToken);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        List<Info> keywordsNotices = infoRepositoryCustomImpl.findInfoByTitles(keywords);

        List<InfoDto.Get> infoDtos = new ArrayList<InfoDto.Get>();
        for (Info keywordsNotice : keywordsNotices) {
            infoDtos.add(modelMapper.map(keywordsNotice, InfoDto.Get.class));
        }

        Page<InfoDto.Get> infoDtoPage = new PageImpl<InfoDto.Get>(infoDtos, pageable, infoDtos.size());

        return infoDtoPage;
    }
}