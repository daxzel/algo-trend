package algo.algorithm.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleMatchingTestCase {

    @Test
    public void testMatchFound() {
        char[] text = new char[]{'s', 't', '2', 'p', '9'};
        char[] word = new char[]{'2', 'p'};
        int index = SimpleMatching.find(word, text);
        assertEquals(index, 2);
    }

    @Test
    public void testMatchNotFound() {
        char[] text = new char[]{'s', 't', '2', 'p', '9'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = SimpleMatching.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testMatchNotFoundSmallText() {
        char[] text = new char[]{'s', 't'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = SimpleMatching.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testMatchFoundWholeMatch() {
        char[] text = new char[]{'2', 'p', '8'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = SimpleMatching.find(word, text);
        assertEquals(index, 0);
    }

    @Test
    public void testNotFoundEmptyText() {
        char[] text = new char[0];
        char[] word = new char[]{'2', 'p', '8'};
        int index = SimpleMatching.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testNotFoundEmptyWord() {
        char[] text = new char[0];
        char[] word = new char[0];
        int index = SimpleMatching.find(word, text);
        assertEquals(index, -1);
    }

}
