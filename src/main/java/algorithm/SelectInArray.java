package algorithm;

public class SelectInArray {

    public static boolean find(int target, int[][] array) {
        // 先确定计算方向
        if (array.length < array[0].length) {
            // 再确定具体元素
            for (int i = 0; i < array.length; i++) {
                int start = 0;
                int end = array.length - 1;
                int mid = start + (end - start) / 2;
                while (true) {
                    if (target < array[i][mid]) {
                        end = mid;
                    } else if (target == array[i][mid]) {
                        return true;
                    } else {
                        start = mid;
                    }
                    mid = start + (end - start) / 2;
                    if (start + 1 == end) {
                        if (target == array[i][start] || target == array[i][end]) {
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < array[0].length; i++) {
                int start = 0;
                int end = array[0].length - 1;
                int mid = start + (end - start) / 2;
                while (true) {
                    if (target < array[mid][i]) {
                        end = mid;
                    } else if (target == array[mid][i]) {
                        return true;
                    } else {
                        start = mid;
                    }
                    mid = start + (end - start) / 2;
                    if (start + 1 == end) {
                        if (target == array[start][i] || target == array[end][i]) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target = 7;
        System.out.println(find(target, array));
    }
}
