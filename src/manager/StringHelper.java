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
        String [] result = str.split("(?<=[\\S][!?.])\\s+");
        return Arrays.asList(result);
    }
}
