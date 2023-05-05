package kyzzhibek.orozbekova.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class KyrgyzTextAnalyzerController {

    @Autowired
    private KyrgyzTextAnalyzerService kyrgyzTextAnalyzerService;

    @GetMapping("/frequency-analysis")
    public Map<Character, Integer> getFrequencyAnalysis() throws IOException {
        return kyrgyzTextAnalyzerService.getFrequencyAnalysis();
    }

    @PutMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedText) throws IOException {
        return kyrgyzTextAnalyzerService.decrypt(encryptedText);
    }
}

