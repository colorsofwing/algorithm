package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class UglyNumber {

    public static int GetUglyNumber_Solution(int index) {
        if (index < 1) return 0;
        if (index == 1) return 1;
        if (index == 2) return 2;
        if (index == 3) return 3;
        if (index == 4) return 4;
        if (index == 5) return 5;
        Integer result = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int num = 0;
        while (num + 5 < index) {
            Integer tail = list.peekLast();
            Integer max1 = 0;
            for (Integer i : list) {
                if (2 * i > tail) {
                    max1 = 2 * i;
                    break;
                }
            }
            Integer max2 = 0;
            for (Integer j : list) {
                if (3 * j > tail) {
                    max2 = 3 * j;
                    break;
                }
            }
            Integer max3 = 0;
            for (Integer k : list) {
                if (5 * k > tail) {
                    max3 = 5 * k;
                    break;
                }
            }
            result = Math.min(Math.min(max1, max2), max3);
            list.add(result);
            while (true) {
                if (list.peekFirst() * 5 <= list.peekLast()) {
                    list.pop();
                } else {
                    break;
                }
            }
            num++;
        }
        return result;
    }

    public static void main(String[] args) {
        GetUglyNumber_Solution(7);
    }
}
