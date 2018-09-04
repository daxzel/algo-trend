package algo.algorithm.string

import org.testng.Assert;

class SimpleMatchingTestCase extends GroovyTestCase {

    void testMatchFound() {
        char[] text = ['s', 't', '2', 'p', '9']
        char[] word = ['2', 'p']
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, 2)
    }

    void testMatchNotFound() {
        char[] text = ['s', 't', '2', 'p', '9']
        char[] word = ['2', 'p', '8']
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testMatchNotFoundSmallText() {
        char[] text = ['s', 't']
        char[] word = ['2', 'p', '8']
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testMatchFoundWholeMatch() {
        char[] text = ['2', 'p', '8']
        char[] word = ['2', 'p', '8']
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, 0)
    }

    void testNotFoundEmptyText() {
        char[] text = []
        char[] word = ['2', 'p', '8']
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, -1)
    }

    void testNotFoundEmptyWord() {
        char[] text = []
        char[] word = []
        def index = SimpleMatching.find(word, text)
        Assert.assertEquals(index, -1)
    }
}
