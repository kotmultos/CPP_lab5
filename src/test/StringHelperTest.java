package test;

import main.manager.StringHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {
    @Test
    void testConvertTextToNormalState() {
        {
            String str = "     here is       an awful sentence          .";
            assertEquals("here is an awful sentence.", StringHelper.convertTextToNormalState(str));
        }
        {
            String str = " а         якщо передати          щось інакше ! ? ! ... ";
            assertEquals("а якщо передати щось інакше!?!...", StringHelper.convertTextToNormalState(str));
        }
        {
            String str = "     якесь     дивне\t речення передано\n\n\n у функцію     !     ?        ";
            assertEquals("якесь дивне речення передано у функцію!?", StringHelper.convertTextToNormalState(str));
        }
    }

    @Test
    void testRemoveOddSpaces() {
        {
            String str = "     here is       an awful sentence.";
            assertEquals("here is an awful sentence.", StringHelper.removeOddSpaces(str));
        }
        {
            String str = "";
            assertEquals("", StringHelper.removeOddSpaces(str));
        }
        {
            String str = "     якесь     дивне\t речення передано\n\n\n у функцію!?";
            assertEquals("якесь дивне речення передано у функцію!?", StringHelper.removeOddSpaces(str));
        }
    }

    @Test
    void testRemoveSpaceBeforeSentenceEnd() {
        {
            String str = "here is an awful sentence    . And another not good sentence     \t!?";
            assertEquals("here is an awful sentence. And another not good sentence!?", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
        {
            String str = "and here we have no any match.";
            assertEquals("and here we have no any match.", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
        {
            String str = "якесь дивне речення передано у функцію      !       ?";
            assertEquals("якесь дивне речення передано у функцію!?", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
    }

    @Test
    void testSplitOnSentences() {
        {
            List<String> list = new ArrayList<>() {
                {
                    add("sentence number one.");
                    add("інше речення, чом би й не спробувати.");
                    add("What's the problem, dude?");
                    add("Також інше речення!!");
                    add("Sentence.");
                }
            };

            StringBuilder text = new StringBuilder();
            for (var item : list) {
                text.append(item + " ");
            }

            assertEquals(list, StringHelper.splitOnSentences(text.toString()));
        }
        {
        List<String> list = new ArrayList<>() {
            {
                add("sentence number 1.");
                add("інше речення, чом би й не спробувати.");
                add("What's the problem, dude?");
                add("Речення з багатьма знаками питання в кінці????????????");
                add("Також інше речення!!");
                add("Sentence.");
                add("Sentence.");
                add("Sentence.");
                add("Sentence.");
                add("Sentence.");
                add("Sentence.");
                add("і останнє без знака в кінці");
            }
        };

        StringBuilder text = new StringBuilder();
        for (var item : list) {
            text.append(item + " ");
        }

        assertEquals(list, StringHelper.splitOnSentences(text.toString()));
        }
    }

    @Test
    void testFindFirstWordStartWithVowel() {
        {
            String str = "here we have Aword starting with a vowel?";
            assertEquals("Aword", StringHelper.findFirstWordStartWithVowel(str));
        }
        {
            String str = "no word start with vowel here!";
            assertNull(StringHelper.findFirstWordStartWithVowel(str));
        }

        {// ukrainian а
            String str = "цікаво, хто спалить аа мости, може, це буду я?..";
            assertEquals("аа", StringHelper.findFirstWordStartWithVowel(str));
        }
        {// ukrainian А
            String str = "цікаво, хто спалить АААА мости, може, це буду я?..";
            assertEquals("АААА", StringHelper.findFirstWordStartWithVowel(str));
        }
        {//english A
            String str = "цікаво, хто спалить AAAA мости, може, це буду я?..";
            assertEquals("AAAA", StringHelper.findFirstWordStartWithVowel(str));
        }

        {
            String str = "цікаво, хто спалить мости, може, це буду я?..";
            assertEquals("я", StringHelper.findFirstWordStartWithVowel(str));
        }

        {
            String str = "Але якщо зробити вот так";
            assertEquals("Але", StringHelper.findFirstWordStartWithVowel(str));
        }
        {
            String str = "однак Але якщо зробити вот так";
            assertEquals("однак", StringHelper.findFirstWordStartWithVowel(str));
        }

        {
            String str = "_it's _interesting to find _if _it works all!";
            assertEquals("all", StringHelper.findFirstWordStartWithVowel(str));
        }
        {
            String str = "_it's _interesting to find _if _it works All!";
            assertEquals("All", StringHelper.findFirstWordStartWithVowel(str));
        }
    }

    @Test
    void testFindLongestWord() {
        {
            String str = "Ангел мій коханий, ти просто чудо!?";
            assertEquals("коханий", StringHelper.findLongestWord(str));
        }
        {
            String str = "lets find the looooooooooongest word?!";
            assertEquals("looooooooooongest", StringHelper.findLongestWord(str));
        }
        {
            String str = "а давайте спробуємо знайти найдовше слово ааааааааааааааааааааааа_ось_воно!!";
            assertEquals("ааааааааааааааааааааааа_ось_воно", StringHelper.findLongestWord(str));
        }
        {
            String str = "два три одн ако віс сло вац іка воп ере вір ити";
            assertNull(StringHelper.findLongestWord(str));
        }
    }

    @Test
    void testSwapWordsInString() {
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "Aswap1";

            assertEquals("string Aswap1 here words swaaaaaaaaaaaaapppppppp2", StringHelper.swapWordsInString(str, word1, word2));
        }

        {
            String str = "string Aswap1 here words swaaaaaaaaaaaaapppppppp2";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }

        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "Aswap1";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }

        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "testWord";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }

        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "testWord";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }

        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "dfdfddfdf";
            String word2 = "testWord";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }
    }
}