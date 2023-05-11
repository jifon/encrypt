package kyzzhibek.orozbekova.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

//@RestController
@Controller
public class KyrgyzTextAnalyzerController {

    @Autowired
    private KyrgyzTextAnalyzerService kyrgyzTextAnalyzerService;

    @GetMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String input, Model model) throws IOException {
        // здесь нужно написать логику расшифровки текста
        Request encryptedText = new Request(input);

        String result = kyrgyzTextAnalyzerService.decrypt(encryptedText); // результат расшифровки
        model.addAttribute("result", result);
        return "index";
    }

//    @GetMapping("/frequency-analysis")
//    public Map<Character, Integer> getFrequencyAnalysis() throws IOException {
//        return kyrgyzTextAnalyzerService.getFrequencyAnalysis();
//    }
//
//    @PutMapping("/decrypt")
//    public String decrypt(@RequestBody Request encryptedText) throws IOException {
//        return kyrgyzTextAnalyzerService.decrypt(encryptedText);
//    }
}

