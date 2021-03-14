package algo.algorithm.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Tsarevskiy
 */
public class AprioriTests {

    @Test
    public void testApriori() {

        List<Set<String>> transactions = new ArrayList<>();

        Set<String> set = new HashSet<>();
        set.add("citrus fruit");
        set.add("semi-finished bread");
        set.add("margarine");
        set.add("ready soups");
        transactions.add(set);
        set = new HashSet<>();
        set.add("tropical fruit");
        set.add("yogurt");
        set.add("coffee");
        transactions.add(set);
        set = new HashSet<>();
        set.add("whole milk");
        transactions.add(set);
        set = new HashSet<>();
        set.add("pip fruit");
        set.add("yogurt");
        set.add("cream cheese");
        set.add("meat spreads");
        transactions.add(set);
        set = new HashSet<>();
        set.add("other vegetables");
        set.add("whole milk");
        set.add("condensed milk");
        set.add("long life bakery product");
        set.add("cereals");
        transactions.add(set);
        set = new HashSet<>();
        set.add("whole milk");
        set.add("butter");
        set.add("yogurt");
        set.add("rice");
        set.add("abrasive cleaner");
        transactions.add(set);
        set = new HashSet<>();
        set.add("rolls / buns");
        transactions.add(set);
        set = new HashSet<>();
        set.add("other vegetables");
        set.add("UHT - milk");
        set.add("rolls / buns");
        set.add("bottled beer");
        set.add("liquor(appetizer)");
        transactions.add(set);
        set = new HashSet<>();
        set.add("pot plants");
        transactions.add(set);
        set = new HashSet<>();
        set.add("whole milk");
        set.add("cereals");
        transactions.add(set);
        Map<Set<String>, Double> map = Apriori.analysis(transactions, 0.1, 5);

        for (Entry<Set<String>, Double> setDoubleEntry : map.entrySet()) {
            for (Set<String> strings : map.keySet()) {
                for (String string : strings) {
                    System.out.print(string + " | ");
                }
            }
            System.out.println(" -> " + setDoubleEntry.getValue());
        }


        assertEquals(map.size(), 1);
        assertEquals(map.values().iterator().next(), 0.2);
        assertEquals(map.keySet().iterator().next(), set); // whole milk and cereals appearing twice
    }

}
