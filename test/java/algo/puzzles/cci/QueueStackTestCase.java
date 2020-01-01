package algo.puzzles.cci;

import static algo.puzzles.list.RemoveDuplicates.question1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class QueueStackTestCase {

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
