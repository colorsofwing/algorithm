package algorithm;

import java.util.ArrayList;

public class FindTwoNumbers {

    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (array == null || array.length <= 1) return list;
        Integer product = null;
        int element = 0;
        for (int i = 0; i < array.length; i++) {
            int num = sum - array[i];
            if (array[i] <= num && FindNumber(array, num, i)) {
                if (product == null || array[i] * num < product) {
                    product = new Integer(array[i] * num);
                    element = array[i];
                }
            }
        }
        if (product != null) {
            list.add(element);
            list.add(sum - element);
        }
        return list;
    }

    public static boolean FindNumber(int[] array, int num, int index) {
        int start = 0;
        int end = array.length - 1;
        while (true) {
            int mid = (start + end) / 2;
            if (start == mid) {
                if (array[start] == num && index != start) {
                    return true;
                } else if (array[end] == num && index != end) {
                    return true;
                }
                break;
            }
            if (array[mid] == num) {
                int pre = mid - 1;
                int next = mid + 1;
                if (mid == index && array[pre] < num && array[next] > num) break;
                return true;
            }
            if (array[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 7, 11, 15};
        FindNumbersWithSum(arr, 15);
    }
}
