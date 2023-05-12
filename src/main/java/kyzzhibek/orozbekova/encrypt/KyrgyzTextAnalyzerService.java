package kyzzhibek.orozbekova.encrypt;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    public String decrypt(Request encryptedText) throws IOException {
        Map<Character, Integer> encryptedFreqMap = getFrequencyAnalysis(encryptedText.getText().toLowerCase());
        Map.Entry<Character, Integer>[] encryptedFreqArray = getSortedFrequencies(encryptedFreqMap);
        Map.Entry<Character, Integer>[] kyrgyzFreqArray = getSortedFrequencies(getFrequencyAnalysis());

        System.out.println("encryptedFreqArray");
        for (Map.Entry<Character, Integer> x : encryptedFreqArray){
            System.out.println(x.getKey()+"  "+ x.getValue());
        }


        System.out.println("kyrgyzFreqArray");
        for (Map.Entry<Character, Integer> x : kyrgyzFreqArray){
            System.out.println(x.getKey()+"  "+ x.getValue());
        }


        System.out.println("\n mapp");
        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < encryptedFreqArray.length; i++) {
            mapping.put(encryptedFreqArray[i].getKey(), kyrgyzFreqArray[i].getKey());
            System.out.println( encryptedFreqArray[i].getKey()+ " - " +kyrgyzFreqArray[i].getKey());
        }

        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.getText().toLowerCase().toCharArray()) {
            decryptedText.append(mapping.getOrDefault(c,c));

        }

        return decryptedText.toString();
    }

    private Map<Character, Integer> getFrequencyAnalysis(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                frequencyMap.merge(c, 1, Integer::sum);
            }
        }
        return frequencyMap;
    }
    @SuppressWarnings("unchecked")
    private Map.Entry<Character, Integer>[] getSortedFrequencies(Map<Character, Integer> frequencyMap) {
        Map.Entry<Character, Integer>[] entries = frequencyMap.entrySet().toArray(new Map.Entry[0]);
        Arrays.sort(entries, (a, b) -> b.getValue().compareTo(a.getValue()));
        return entries;
    }

    public String decryptWithReplacements(Request request, Map<Character, Character> replacementsMap) {
        String encryptedText = request.getText();
        StringBuilder decryptedTextBuilder = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);
            char decryptedChar;

            if (replacementsMap.containsKey(currentChar)) {
                decryptedChar = replacementsMap.get(currentChar);
            } else {
                decryptedChar = currentChar;
            }

            decryptedTextBuilder.append(decryptedChar);
        }

        return decryptedTextBuilder.toString();
    }
//    public String decrypt(Request encryptedText) throws IOException {
//        Map<Character, Integer> frequencyMap = getFrequencyAnalysis();
//
//        System.out.println(encryptedText.getText());
//        encryptedText.setText(encryptedText.getText().toLowerCase());
//
//        StringBuilder decryptedTextBuilder = new StringBuilder();
//
//        for (char c : encryptedText.getText().toCharArray()) {
//            if (ALPHABET.indexOf(c) != -1) {
//                Optional<Map.Entry<Character, Integer>> maxEntry = frequencyMap.entrySet()
//                        .stream()
//                        .filter(entry -> Character.toLowerCase(entry.getKey()) == c)
//                        .max(Map.Entry.comparingByValue());
//
//                if (maxEntry.isPresent()) {
//                    System.out.println(maxEntry.get().getKey());
//                    decryptedTextBuilder.append(maxEntry.get().getKey());
//                } else {
//                    decryptedTextBuilder.append(c);
//                }
//            } else {
//                decryptedTextBuilder.append(c);
//            }
//        }
//
//        return decryptedTextBuilder.toString();
//    }
}

