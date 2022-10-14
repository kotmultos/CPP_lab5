package manager;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    public static String convertTextToNormalState(String text) {
        return removeSpaceBeforeSentenceEnd(removeOddSpaces(text));
    }

    private static String removeOddSpaces(String str) {
        // replace 2 or more whitespaces with just 1
        Pattern pattern = Pattern.compile("\s{2,}");  // Creating a pattern for whitespaces
        Matcher mat = pattern.matcher(str);                   // Searching pattern in str
        return mat.replaceAll(" ");                 // Replacing 2 or more spaces
    }

    // tmp method for faster testing before tests are written
    private static String removeSpaceBeforeSentenceEnd(String str) {
        // Creating a pattern for spaces between word and symbol of end of the sentence
        Pattern pattern = Pattern.compile("\\s+(?=\\p{Punct})");
        Matcher mat = pattern.matcher(str);                     // Searching pattern in str
        return mat.replaceAll("");                    // Replacing
    }

    public static List<String> splitOnSentences(String str) {
        String[] result = str.split("(?<=[\\S][!?.])\\s+");
        return Arrays.asList(result);
    }

    public static String findFirstWordStartWithVowel(String sentence) {
        String result = null;
        Pattern pattern = Pattern.compile("\\b[aeiouаеиіоуяюєї]\\S*\\b\\s{1}", Pattern.CASE_INSENSITIVE);
        Matcher mat = pattern.matcher(sentence);

        boolean tmp = mat.find();
        if (tmp) {
            result = mat.group();
        }
        return result;
    }

    public static String findLongestWord(String sentence) {
        String [] words = sentence.split("[\\s!?.,]+");
        int pos = 0;
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > words[pos].length()) {
                pos = i;
            }
        }

        return words[pos];
    }

    public static String swapWordsInString(String sentence, String firstWordStartWithVowel, String longestWord) {
        // to be implemented ;)
        return sentence;
    }
}