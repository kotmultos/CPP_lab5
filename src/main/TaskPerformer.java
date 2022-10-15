package main;

import main.manager.DisplayManager;
import main.manager.InputManager;
import main.manager.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskPerformer {
    private String text;

    public TaskPerformer() {
        System.out.println("Введіть бажаний текст. Для завершення вводу натисність \"enter\" в новій стрічці");
        text = InputManager.getStringsFromConsole();
//        text = InputManager.getStringsFromFile("input.txt");
    }

    public void performLab() {
        // check if user input is empty string
        if (text.isBlank()) {
            DisplayManager.print("На жаль, користувач не ввів текст. Перезапустіть програму і введіть текст..");
        } else {
            DisplayManager.print("Ввід користувача:", text);

            // remove odd whitespaces
            text = StringHelper.convertTextToNormalState(text);
            DisplayManager.print("Нормалізований текст", text);

            // split on sentences
            List<String> sentences = StringHelper.splitOnSentences(text);
            DisplayManager.print("Текст, поділений на речення", sentences);

            List<String> result = swapWordsByCondition(sentences);
            DisplayManager.print("** Результат роботи програми **", result);
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
                DisplayManager.print("Речення \"" + sentence + "\" не містить слів, що починаються на голосну. Перестановка неможлива.");
                resultSentences.add(sentence);
            } else if (firstWordStartWithVowel.equals(longestWord)) {   // both words are equal
                DisplayManager.print("У реченні \"" + sentence +
                        "\" найдовше слово і перше слово, що починається на голосну, однакові (\"" + longestWord + "\"). Перестановка неможлива.");
                resultSentences.add(sentence);
            } else {                                                    // if both words are found, swap them in sentence
                String newSentence = StringHelper.swapWordsInString(sentence, firstWordStartWithVowel, longestWord);    // swap words
                resultSentences.add(newSentence);
            }
        }
        return resultSentences;
    }
}
