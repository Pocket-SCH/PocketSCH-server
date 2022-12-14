package gdscsch.PocketSCHserver.bus.service;

import gdscsch.PocketSCHserver.bus.dto.ImageFileDto;
import gdscsch.PocketSCHserver.bus.entity.ImageFile;
import gdscsch.PocketSCHserver.bus.repository.ImageFileRepository;
import gdscsch.PocketSCHserver.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageFileService {
    private final ImageFileRepository imageFileRepository;

    public Integer saveFile(ImageFileDto fileDto) {
        return imageFileRepository.save(ImageFile.builder()
                .token(fileDto.getTokenStr())
                .filePath(fileDto.getFilePath())
                .fileName(fileDto.getFileName())
                .origFilename(fileDto.getOrigFilename())
                .build()).getId();
    }
}
