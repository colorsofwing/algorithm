package algorithm;

import java.util.Arrays;

public class MergeSort {

    /**
     * 有序数组合并
     *
     * @param arr
     * @param start
     * @param mid
     * @param end
     */
    public static void merge(int[] arr, int start, int mid, int end) {
        // 创建临时数组
        int[] temp = new int[end - start + 1];
        // 定义mid前后的指针
        int p1 = start;
        int p2 = mid + 1;
        // 定义临时数组指针
        int i = 0;
        // 比较两部分的元素，小的先放入临时数组
        while (p1 <= mid && p2 <= end) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 将剩余元素加入临时数组
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= end) {
            temp[i++] = arr[p2++];
        }
        // 临时数组元素复制回原来的数值
        for (int j = 0; j <= temp.length - 1; j++) {
            arr[start + j] = temp[j];
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 排序
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void sort(int[] arr, int start, int end) {
        if (start == end) return;
        int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 8, 5, 2, 4, 9, 15, 1};
        sort(arr, 0, arr.length - 1);
    }
}
