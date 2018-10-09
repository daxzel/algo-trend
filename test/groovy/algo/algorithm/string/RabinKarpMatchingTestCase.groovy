package algo.algorithm.string

import org.junit.Assert

class RabinKarpMatchingTestCase extends GroovyTestCase {

    void testMatchFound() {
        char[] text = ['s', 't', '2', 'p', '9']
        char[] word = ['2', 'p']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, 2)
    }

    void testMatchNotFound2() {
        char[] text = ['s', 't', '2', 'p', '9', 'a', 'b', '2', 't']
        char[] word = ['2', 'p', 't']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testMatchFound2() {
        char[] text = ['s', 't', '2', 'p', '9', 'a', 'b', '2', 'p', 't', '1', 'a']
        char[] word = ['2', 'p', 't']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, 7)
    }

    void testMatchNotFound() {
        char[] text = ['s', 't', '2', 'p', '9']
        char[] word = ['2', 'p', '8']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testMatchNotFoundSmallText() {
        char[] text = ['s', 't']
        char[] word = ['2', 'p', '8']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testMatchFoundWholeMatch() {
        char[] text = ['2', 'p', '8']
        char[] word = ['2', 'p', '8']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, 0)
    }

    void testNotFoundEmptyText() {
        char[] text = []
        char[] word = ['2', 'p', '8']
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testNotFoundEmptyWord() {
        char[] text = []
        char[] word = []
        def index = RabinKarpAlgorithm.find(word, text)
        Assert.assertEquals(index, -1)
    }
}
