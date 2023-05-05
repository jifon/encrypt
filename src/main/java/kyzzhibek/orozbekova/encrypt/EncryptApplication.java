package kyzzhibek.orozbekova.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class EncryptApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EncryptApplication.class, args);
        // Путь к файлу со стихами
        String filePath = "src/main/resources/File/Data";

    }

}
