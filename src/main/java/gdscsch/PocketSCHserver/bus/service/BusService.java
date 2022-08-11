package gdscsch.PocketSCHserver.bus.service;

import gdscsch.PocketSCHserver.bus.dto.BusTimeDto;
import gdscsch.PocketSCHserver.bus.dto.ImageFileDto;
import gdscsch.PocketSCHserver.bus.entity.ImageFile;
import gdscsch.PocketSCHserver.bus.repository.BusRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepositorySupport busRepositorySupport;

    public List<BusTimeDto> getSubwayTimeByWeekDay(Integer type, Integer weekday) {
        List<BusTimeDto> busTimes = busRepositorySupport.findAllSubwayTimeByWeekDay(type, weekday);

        return busTimes;
    }
}