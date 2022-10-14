import manager.DisplayManager;
import manager.InputManager;
import manager.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskPerformer {
    private String text;

    public TaskPerformer() {
//        text = InputManager.getStringsFromConsole("Введіть стрічки тексту:");
        text = InputManager.getStringsFromFile("input.txt");
    }

    public void performLab() {
        // check if user input is empty string
        if (text.isBlank()) {
            DisplayManager.PrintMessage("На жаль, користувач не ввів текст. Перезапустіть програму і введіть текст..");
        } else {
            DisplayManager.Print("Ввід користувача:", text);

            // remove odd whitespaces
            text = StringHelper.convertTextToNormalState(text);
            DisplayManager.Print("Нормалізований текст", text);

            // split on sentences
            List<String> sentences = StringHelper.splitOnSentences(text);
            DisplayManager.Print("Текст, поділений на речення", sentences);

            List<String> result = swapWordsByCondition(sentences);
            DisplayManager.Print("** Результат роботи програми **", result);
        }
    }
    private List<String> swapWordsByCondition(List<String> sentences) {
        List<String> resultSentences = new ArrayList<>();
        // from here I need to work with each sentence separately
        for (var sentence : sentences) {
            // find the first word beginning with vowel
            String firstWordStartWithVowel = StringHelper.findFirstWordStartWithVowel(sentence);
            // find the longest word
            String longestWord = StringHelper.findLongestWord(sentence);

            // deal with situations when swap is not available
            if (firstWordStartWithVowel == null) {                      // no words starting with vowel
                DisplayManager.PrintMessage("Речення " + sentence + " не містить слів, що починаються на голосну. Перестановка неможлива.");
                resultSentences.add(sentence);
            } else if (firstWordStartWithVowel.equals(longestWord)) {   // both words are equal
                DisplayManager.PrintMessage("У реченні " + sentence +
                        " найдовше слово і перше слово, що починається на голосну, однакові. Перестановка неможлива.");
                resultSentences.add(sentence);
            } else {                                                    // if both words are found, swap them in sentence
                String newSentence = "test";    // swap words
                resultSentences.add(newSentence);
            }
        }
        return resultSentences;
    }
}
