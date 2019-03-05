package tatc.util;

import java.util.*;

public class Combinatorics {
    public static <T> List<List<T>> combination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.<T> emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<T>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }

        combination.addAll(combination(subSet, size));

        return combination;
    }

    public static <T> ArrayList<ArrayList<T>> powerSet(ArrayList<T> originalSet) {
        ArrayList<ArrayList<T>> sets = new ArrayList();
        if (originalSet.isEmpty()) {
            sets.add(new ArrayList());
            return sets;
        } else {
            ArrayList<T> list = new ArrayList(originalSet);
            T head = list.get(0);
            ArrayList<T> rest = new ArrayList(list.subList(1, list.size()));
            Iterator var5 = powerSet(rest).iterator();

            while(var5.hasNext()) {
                ArrayList<T> set = (ArrayList)var5.next();
                ArrayList<T> newSet = new ArrayList();
                newSet.add(head);
                newSet.addAll(set);
                sets.add(newSet);
                sets.add(set);
            }

            return sets;
        }
    }

    public static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }
}
