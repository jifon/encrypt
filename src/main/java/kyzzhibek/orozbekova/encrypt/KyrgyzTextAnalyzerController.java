package kyzzhibek.orozbekova.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@RestController
@Controller
public class KyrgyzTextAnalyzerController {

    @Autowired
    private KyrgyzTextAnalyzerService kyrgyzTextAnalyzerService;

    @GetMapping({"/","/index"})
    public String index(Model model) {
        model.addAttribute("inputText", ""); // добавляем пустой текст по умолчанию
        return "index";
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String inputText, Model model) throws IOException {
        // здесь нужно написать логику расшифровки текста
        Request encryptedText = new Request(inputText);

        String result = kyrgyzTextAnalyzerService.decrypt(encryptedText); // результат расшифровки
        model.addAttribute("inputText", inputText);
        model.addAttribute("result", result);

        return "index";
    }


    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("encryptedText", "");
        model.addAttribute("replacements", "");
        model.addAttribute("decryptedText", "");
        return "edit";
    }

    @PostMapping("/editions")
    public String editions(@RequestParam String encryptedText, @RequestParam String replacements, Model model) throws IOException {
        // разбиваем замены на ключи и значения
        Map<Character, Character> replacementsMap = new HashMap<>();
        String[] pairs = replacements.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("-");
            replacementsMap.put(keyValue[0].charAt(0), keyValue[1].charAt(0));
        }



        for (Map.Entry<Character,Character> entry : replacementsMap.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());



        // расшифровываем текст с учетом замен
        Request request = new Request(encryptedText);
        String decryptedText = kyrgyzTextAnalyzerService.decryptWithReplacements(request, replacementsMap);

        System.out.println("decryptedText");
        System.out.println(decryptedText);
        // передаем результаты расшифровки и введенные данные на страницу
        model.addAttribute("encryptedText", encryptedText);
        model.addAttribute("replacements", replacements);
        model.addAttribute("decryptedText", decryptedText);
        return "edit";
    }

//    @GetMapping("/edit")
//    public String edit(Model model) {
//        model.addAttribute("inputCode", ""); // добавляем пустой текст по умолчанию
//        return "index";
//    }

//    @GetMapping({"/","/index"})
//    public String index(@ModelAttribute("input") Request request, Model model) {
//        model.addAttribute("input", request.getText());
//        return "index";
//    }
//
//    @PostMapping("/decrypt")
//    public String decrypt(@ModelAttribute("input") Request request, Model model) throws IOException {
//        String input = request.getText();
//        Request encryptedText = new Request(input);
//
//        String result = kyrgyzTextAnalyzerService.decrypt(encryptedText); // результат расшифровки
//        model.addAttribute("result", result);
//
//        return "index";
//    }


//    @GetMapping({"/","/index"})
//    public String index() {
//        return "index";
//    }
//
//    @PostMapping("/decrypt")
//    public String decrypt(@RequestParam String input, Model model) throws IOException {
//        // здесь нужно написать логику расшифровки текста
//        Request encryptedText = new Request(input);
//
//        String result = kyrgyzTextAnalyzerService.decrypt(encryptedText); // результат расшифровки
//        model.addAttribute("result", result);
//        return "index";
//    }

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

