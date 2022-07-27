package gdscsch.PocketSCHserver.servcie;

import gdscsch.PocketSCHserver.dto.TestDto;
import gdscsch.PocketSCHserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public TestDto testServiceMethod(String str) {
        TestDto testDto = new TestDto();
        testDto.setTestStr(str);

        return testDto;
    }
}