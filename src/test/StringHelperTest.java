package test;

import main.manager.StringHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {
    @Test
    void testConvertTextToNormalState() {
        // spaces at the beginning, middle
        {
            String str = "     here is       an awful sentence          .";
            assertEquals("here is an awful sentence.", StringHelper.convertTextToNormalState(str));
        }
        // spaces at the beginning, middle, end
        {
            String str = "   а         якщо передати          щось \t\t інакше ! ? ! ...      ";
            assertEquals("а якщо передати щось інакше!?!...", StringHelper.convertTextToNormalState(str));
        }
        // spaces at the middle, end
        {
            String str = "якесь     дивне\t речення передано\n\n\n у функцію     !     ?        ";
            assertEquals("якесь дивне речення передано у функцію!?", StringHelper.convertTextToNormalState(str));
        }
        // blank string
        {
            String str = "       ";
            assertEquals("", StringHelper.convertTextToNormalState(str));
        }
        // empty string
        {
            String str = "";
            assertEquals("", StringHelper.convertTextToNormalState(str));
        }
    }

    @Test
    void testRemoveOddSpaces() {
        // spaces at the beginning
        {
            String str = "     here is       an awful sentence.";
            assertEquals("here is an awful sentence.", StringHelper.removeOddSpaces(str));
        }
        // empty string
        {
            String str = "";
            assertEquals("", StringHelper.removeOddSpaces(str));
        }
        // spaces at the beginning, middle and end of string
        {
            String str = "     якесь     дивне\t речення              передано\n\n\n у функцію!?         ";
            assertEquals("якесь дивне речення передано у функцію!?", StringHelper.removeOddSpaces(str));
        }
    }

    @Test
    void testRemoveSpaceBeforeSentenceEnd() {
        // 1 space and several spaces between word and [!.?]
        {
            String str = "here is an awful sentence . And another not good sentence       \t!       ?";
            assertEquals("here is an awful sentence. And another not good sentence!?", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
        // nothing to remove
        {
            String str = "and here we don't have any match.";
            assertEquals("and here we don't have any match.", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
        // multiple [!?.] in a row
        {
            String str = "якесь дивне речення передано у функцію      !?? ! ! !       ?";
            assertEquals("якесь дивне речення передано у функцію!??!!!?", StringHelper.removeSpaceBeforeSentenceEnd(str));
        }
    }

    @Test
    void testSplitOnSentences() {
        // only one sentence
        {
            List<String> list = new ArrayList<>() {
                {
                    add("the only sentence here!?");
                }
            };

            StringBuilder text = new StringBuilder();
            for (var item : list) {
                text.append(item + " ");
            }

            assertEquals(list, StringHelper.splitOnSentences(text.toString()));
        }
        // several sentences
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
        // empty string
        {
            String text = "";

            assertTrue(StringHelper.splitOnSentences(text).isEmpty());
        }
    }

    @Test
    void testFindFirstWordStartWithVowel() {
        // no matches
        {
            String str = "no word start with vowel here!";
            assertNull(StringHelper.findFirstWordStartWithVowel(str));
        }
        // no matches
        {
            String str = "сьогодні день такий чудовий";
            assertNull(StringHelper.findFirstWordStartWithVowel(str));
        }
        // in the middle
        {
            String str = "here we have Aword starting with a vowel?";
            assertEquals("Aword", StringHelper.findFirstWordStartWithVowel(str));
        }
        // in the middle
        {
            String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
            assertEquals("ipsum", StringHelper.findFirstWordStartWithVowel(str));
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
        // first word
        {
            String str = "Але якщо зробити вот так";
            assertEquals("Але", StringHelper.findFirstWordStartWithVowel(str));
        }
        // first word
        {
            String str = "однак Але якщо зробити вот так";
            assertEquals("однак", StringHelper.findFirstWordStartWithVowel(str));
        }
        // the last word
        {
            String str = "цікаво, хто спалить мости, може, це буду я?..";
            assertEquals("я", StringHelper.findFirstWordStartWithVowel(str));
        }
        // the last word
        {
            String str = "_it's _interesting to find _if _it works all!";
            assertEquals("all", StringHelper.findFirstWordStartWithVowel(str));
        }
        // the last word
        {
            String str = "_it's _interesting to find _if _it works All.";
            assertEquals("All", StringHelper.findFirstWordStartWithVowel(str));
        }
    }

    @Test
    void testFindLongestWord() {
        // in the middle
        {
            String str = "Ангел мій коханий, ти просто чудо!?";
            assertEquals("коханий", StringHelper.findLongestWord(str));
        }
        // at the beginning
        {
            String str = "looooooooooongest lets find the word?!";
            assertEquals("looooooooooongest", StringHelper.findLongestWord(str));
        }
        // in the end
        {
            String str = "а давайте спробуємо знайти найдовше слово ааааааааааааааааааааааа_ось_воно!!";
            assertEquals("ааааааааааааааааааааааа_ось_воно", StringHelper.findLongestWord(str));
        }
        // all words are equal of the same length
        {
            String str = "два три одн ако віс сло вац іка воп ере вір ити";
            assertNull(StringHelper.findLongestWord(str));
        }
        // several words are equal of the same length
        {
            String str = "два три однакові сслова цікаво перевірю";
            assertNull(StringHelper.findLongestWord(str));
        }
    }

    @Test
    void testSwapWordsInString() {
        // longest before vowel
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1 sdf s sdfsdf ";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "Aswap1";

            assertEquals("string Aswap1 here words swaaaaaaaaaaaaapppppppp2 sdf s sdfsdf ", StringHelper.swapWordsInString(str, word1, word2));
        }
        // vowel before longest
        {
            String str = "string Aswap1 here words swaaaaaaaaaaaaapppppppp2 sdfsdfsdf sdf sdf";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1 sdfsdfsdf sdf sdf", StringHelper.swapWordsInString(str, word1, word2));
        }
        // attempt to replace the same string
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "Aswap1";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }
        // no such word in a text
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "testWord";
            String word2 = "Aswap1";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }
        // no such word in a text
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "testWord";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }
        // no both words in a text
        {
            String str = "string swaaaaaaaaaaaaapppppppp2 here words Aswap1";
            String word1 = "dfdfddfdf";
            String word2 = "testWord";

            assertEquals("string swaaaaaaaaaaaaapppppppp2 here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }

        // longest is the first, vowel is the last
        {
            String str = "swaaaaaaaaaaaaapppppppp2 string here words Aswap1";
            String word1 = "swaaaaaaaaaaaaapppppppp2";
            String word2 = "Aswap1";

            assertEquals("Aswap1 string here words swaaaaaaaaaaaaapppppppp2", StringHelper.swapWordsInString(str, word1, word2));
        }
        // vowel is the first, longest is the last
        {
            String str = "Aswap1 string here words swaaaaaaaaaaaaapppppppp2";
            String word1 = "Aswap1";
            String word2 = "swaaaaaaaaaaaaapppppppp2";

            assertEquals("swaaaaaaaaaaaaapppppppp2 string here words Aswap1", StringHelper.swapWordsInString(str, word1, word2));
        }
    }
}