package gdscsch.PocketSCHserver.bus.service;

import gdscsch.PocketSCHserver.bus.dto.BusTimeDto;
import gdscsch.PocketSCHserver.bus.dto.ImageFileDto;
import gdscsch.PocketSCHserver.bus.entity.ImageFile;
import gdscsch.PocketSCHserver.bus.repository.BusRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepositorySupport busRepositorySupport;

    public List<BusTimeDto> getSubwayTimeByWeekDay(Integer type, Integer weekday) {
        List<BusTimeDto> busTimes = busRepositorySupport.findAllSubwayTimeByWeekDay(type, weekday);

        if (busTimes.size()==0) {
            busTimes.add(BusTimeDto.builder()
                    .id(-1)
                    .type(-1)
                    .busTime(LocalDateTime.now())
                    .busWeekDay(-1)
                    .build());
        }

        return busTimes;
    }
}