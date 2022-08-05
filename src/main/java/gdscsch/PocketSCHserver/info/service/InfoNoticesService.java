package gdscsch.PocketSCHserver.info.service;

import gdscsch.PocketSCHserver.info.dto.InfoDto;
import gdscsch.PocketSCHserver.info.dto.KeywordDto;
import gdscsch.PocketSCHserver.info.dto.KeywordDto.Get;
import gdscsch.PocketSCHserver.info.entity.Info;
import gdscsch.PocketSCHserver.info.repository.InfoRepository;
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

    @Autowired
    private ModelMapper modelMapper;

    public Page<InfoDto.Get> readNotices(Integer page, Integer size, Integer infoCategoryId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Info> universityNoticesPage = infoRepository.findAllByInfoCategoryId(infoCategoryId, pageable);

        List<InfoDto.Get> infoDtos = new ArrayList<InfoDto.Get>();

        for (Info UniversityNotice : universityNoticesPage.getContent()) {
            infoDtos.add(modelMapper.map(UniversityNotice, InfoDto.Get.class));
        }

        Page<InfoDto.Get> infoDtoPage = new PageImpl<InfoDto.Get>(infoDtos, pageable, universityNoticesPage.getTotalElements());

        return infoDtoPage;
    }
}