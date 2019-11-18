package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContinuousSequenceSum {

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum < 3) return new ArrayList();
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
        for (int i = 2; i < sum; i++) {
            if (i % 2 == 1 && sum % i == 0 && sum / i > i / 2) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                int index = sum / i - i / 2;
                while (index <= sum / i + i / 2) {
                    list.add(new Integer(index));
                    index++;
                }
                resultList.add(list);
            }
            if (i % 2 == 0 && sum % i == i / 2 && sum / i >= i / 2) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                int index = sum / i - i / 2 + 1;
                while (index <= sum / i + i / 2) {
                    list.add(new Integer(index));
                    index++;
                }
                resultList.add(list);
            }
        }
        Collections.sort(resultList, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
                return list1.get(0) - list2.get(0);
            }
        });
        return resultList;
    }

    public static void main(String[] args) {
        FindContinuousSequence(123);
    }
}
