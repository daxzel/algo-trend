package algo.algorithm.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RabinKarpMatchingTestCase {

    @Test
    public void testMatchFound() {
        char[] text = new char[]{'s', 't', '2', 'p', '9'};
        char[] word = new char[]{'2', 'p'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, 2);
    }

    @Test
    public void testMatchNotFound2() {
        char[] text = new char[]{'s', 't', '2', 'p', '9', 'a', 'b', '2', 't'};
        char[] word = new char[]{'2', 'p', 't'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testMatchFound2() {
        char[] text = new char[]{'s', 't', '2', 'p', '9', 'a', 'b', '2', 'p', 't', '1', 'a'};
        char[] word = new char[]{'2', 'p', 't'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, 7);
    }

    @Test
    public void testMatchNotFound() {
        char[] text = new char[]{'s', 't', '2', 'p', '9'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testMatchNotFoundSmallText() {
        char[] text = new char[]{'s', 't'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testMatchFoundWholeMatch() {
        char[] text = new char[]{'2', 'p', '8'};
        char[] word = new char[]{'2', 'p', '8'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, 0);
    }

    @Test
    public void testNotFoundEmptyText() {
        char[] text = new char[0];
        char[] word = new char[]{'2', 'p', '8'};
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, -1);
    }

    @Test
    public void testNotFoundEmptyWord() {
        char[] text = new char[0];
        char[] word = new char[0];
        int index = RabinKarpAlgorithm.find(word, text);
        assertEquals(index, -1);
    }

}
