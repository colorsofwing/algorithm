package algorithm;

import java.util.Arrays;

public class QuickSort {

    /**
     * 排序
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void sort(int[] arr, int start, int end) {
        // 只有一个元素时中断流程
        if (start == end) return;
        // 选择中点，默认为起始点
        int pivot = start;
        // 将中点放到合适的位置，左边小，右边大
        for (int i = start + 1; i <= end; i++) {
            // 右边存在比中点小的元素，需要移至中点左边
            if (arr[i] < arr[pivot]) {
                int temp = arr[i];
                for (int j = i; j > pivot; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[pivot++] = temp;
            }
        }
        // 分块处理
        sort(arr, start, pivot);
        sort(arr, pivot + 1, end);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 8, 5, 2, 4, 9, 15, 4};
        sort(arr, 0, arr.length - 1);
    }
}
