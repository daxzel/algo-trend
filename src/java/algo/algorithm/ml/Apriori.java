/*
  Copyright (c) 2019 Amazon.com, Inc. All rights reserved.
  <p>
  LICENCE: This work is proprietary information of Amazon.com, Inc. and may not
  be redistributed outside of Amazon without explicit written permission. If
  you are not an Amazon employee, please delete this file without reading
  further.
 */

package algo.algorithm.ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Apriori {

    public static Map<Set<String>, Double> analysis(List<Set<String>> items, double minSupport, int maxlen) {

        Set<String> uniqItemSet = uniqItemSet(items);

        Map<Set<String>, Double> result = new HashMap<>();

        List<Set<String>> itemSets = new ArrayList<>();
        for (String uniqItem : uniqItemSet) {
            Set<String> set = new HashSet<>();
            set.add(uniqItem);
            itemSets.add(set);
        }

        int currentCandidateSize = 2;
        while (maxlen >= currentCandidateSize && !itemSets.isEmpty()) {

            List<Set<String>> newItemSets = new ArrayList<>();

            for (Set<String> first : itemSets) {
                for (Set<String> second : itemSets) {
                    Set<String> candidate = new HashSet<>();
                    candidate.addAll(first);
                    candidate.addAll(second);

                    // check for almost intersect
                    if (candidate.size() != second.size() + 1) {
                        continue;
                    }

                    double support = calcSupport(candidate, items);
                    if (support > minSupport) {
                        newItemSets.add(candidate);
                        result.put(candidate, support);
                    }
                }
            }
            itemSets = newItemSets;
            currentCandidateSize++;
        }

        return result;
    }


    private static double calcSupport(Set<String> set, List<Set<String>> items) {
        int frequency = 0;
        for (Set<String> item : items) {
            boolean allMatch = item.containsAll(set);
            if (allMatch) {
               frequency++;
            }
        }
        return frequency / (double) items.size();
    }

    private static Set<String> uniqItemSet(List<Set<String>> items) {
        Set<String> uniqItemSet = new HashSet<>();
        for (Set<String> itemSet : items) {
            uniqItemSet.addAll(itemSet);
        }
        return uniqItemSet;
    }

    private static void createItemSetsOfSize1(int uniqItemSetSize) {
        List<int[]> itemsets = new ArrayList<>();
        for (int i = 0; i < uniqItemSetSize; i++) {
            int[] cand = {i};
            itemsets.add(cand);
        }
    }

}
