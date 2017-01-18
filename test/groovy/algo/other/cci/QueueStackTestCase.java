package algo.other.cci;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static algo.other.cci.LinkedLists.question1;

/**
 * Created by andrey tsarevskiy
 */
public class QueueStackTestCase extends Assert {

    @Test
    public void testDuplicate() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        question1(list);
        assertEquals(list.size(), 1);
    }
}
