package gdscsch.PocketSCHserver.info.service;

import gdscsch.PocketSCHserver.info.dto.InfoDto;
import gdscsch.PocketSCHserver.info.entity.Info;
import gdscsch.PocketSCHserver.info.repository.InfoRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<InfoDto.Get> readNotices(Integer page, Integer size, Integer infoCategoryId) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        List<Info> UniversityNotices = infoRepository.findAllByInfoCategoryId(infoCategoryId, pageable);
        List<InfoDto.Get> infoDtos = new ArrayList<InfoDto.Get>();

        for (Info UniversityNotice : UniversityNotices) {
            infoDtos.add(modelMapper.map(UniversityNotice, InfoDto.Get.class));
        }

        return infoDtos;
    }
}
