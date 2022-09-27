package pl.agnieszkachalat.quizapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public abstract class BaseTest {
    
    protected String getFileAsString(String filePath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
