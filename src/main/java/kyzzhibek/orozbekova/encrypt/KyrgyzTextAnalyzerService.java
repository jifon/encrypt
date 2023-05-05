package kyzzhibek.orozbekova.encrypt;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KyrgyzTextAnalyzerService {

    private static final String FILE_PATH = "src/main/resources/File/Data";
    private static final String ALPHABET = "абвгдеёжзийклмнңоөпрстуүфхцчшъыьэюя";

    public Map<Character, Integer> getFrequencyAnalysis() throws IOException {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        String text = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);

        for (char c : text.toLowerCase().toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                frequencyMap.merge(c, 1, Integer::sum);
            }
        }

        return frequencyMap;
    }

    public String decrypt(String encryptedText) throws IOException {
        Map<Character, Integer> frequencyMap = getFrequencyAnalysis();

        StringBuilder decryptedTextBuilder = new StringBuilder();
        for (char c : encryptedText.toLowerCase().toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                Optional<Map.Entry<Character, Integer>> maxEntry = frequencyMap.entrySet()
                        .stream()
                        .filter(entry -> Character.toLowerCase(entry.getKey()) == c)
                        .max(Map.Entry.comparingByValue());

                if (maxEntry.isPresent()) {
                    decryptedTextBuilder.append(maxEntry.get().getKey());
                } else {
                    decryptedTextBuilder.append(c);
                }
            } else {
                decryptedTextBuilder.append(c);
            }
        }

        return decryptedTextBuilder.toString();
    }
}

