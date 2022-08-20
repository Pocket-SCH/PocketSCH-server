package gdscsch.PocketSCHserver.bus.controller;

import gdscsch.PocketSCHserver.bus.dto.BusTimeDto;
import gdscsch.PocketSCHserver.bus.service.BusService;
import gdscsch.PocketSCHserver.food.entity.FoodMenu;
import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/bus")
public class BusController {
    private final BusService busService;

    @GetMapping("/timelist/{type}/{weekDay}")
    public ResponseEntity getFoodListByCategory(@PathVariable("type") Integer type, @PathVariable("weekDay") Integer weekDay) {
        List<BusTimeDto> busTimes = busService.getSubwayTimeByWeekDay(type, weekDay);
        System.out.println("-----here111 + " + busTimes.size());

        return busTimes.size() != 0 ? new ResponseEntity(DefaultRes.res(StatusCode.OK, "요일별 시간표 반환 완료", busTimes), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "해당 요일 시간 없음", busTimes), HttpStatus.OK);
    }
}
