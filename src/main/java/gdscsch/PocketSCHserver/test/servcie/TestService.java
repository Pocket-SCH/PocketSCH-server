package gdscsch.PocketSCHserver.test.servcie;

import gdscsch.PocketSCHserver.test.dto.TestDto;
import gdscsch.PocketSCHserver.test.repository.TestRepository;
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