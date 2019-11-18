package algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class MinStringFromArray {

    public static String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int num : numbers) {
            list.add(new Integer(num));
        }
        Collections.sort(list);
        String result = "";
        for (Integer num : list) {
            if (result.isEmpty()) {
                result = num.toString();
                continue;
            }
            int a = 0;
            int b = 0;
            int index = 0;
            int l1 = result.toCharArray().length;
            int l2 = num.toString().toCharArray().length;
            while (a == b) {
                a = index > l1 - 1 ? result.toCharArray()[l1 - 1] : result.toCharArray()[index];
                b = index > l2 - 1 ? num.toString().toCharArray()[l2 - 1] : num.toString().toCharArray()[index];
                index++;
                if (index > l1 && index > l2) break;
            }
            result = a < b ? result + num.toString() : num.toString() + result;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3334, 3, 3333332};
        PrintMinNumber(arr);
    }
}
