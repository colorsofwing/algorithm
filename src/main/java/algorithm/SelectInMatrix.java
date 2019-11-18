package algorithm;

import java.util.HashSet;

public class SelectInMatrix {

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows <= 0 || cols <= 0 || str == null) return false;
        if (matrix.length == 0 || str.length == 0) return false;
        if (matrix.length != rows * cols) return false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == str[0]) {
                // 记录已经走过的元素的下标
                HashSet<Integer> set = new HashSet<Integer>();
                set.add(i);
                if (tryPath(matrix, rows, cols, str, set, i, 1)) return true;
            }
        }
        return false;
    }

    public static boolean tryPath(char[] matrix, int rows, int cols, char[] str, HashSet<Integer> set, int pre, int index) {
        if (set.size() == str.length) return true;
        // 往左
        int next1 = pre - 1;
        // 允许向左，元素相等，不能重复
        if (next1 >= 0 && pre % cols != 0 && matrix[next1] == str[index] && !set.contains(next1)) {
            HashSet<Integer> branchSet = (HashSet<Integer>) set.clone();
            branchSet.add(next1);
            if (tryPath(matrix, rows, cols, str, branchSet, next1, index + 1)) return true;
        }
        // 往右
        int next2 = pre + 1;
        // 允许向右，元素相等，不能重复
        if (pre % cols != cols - 1 && matrix[next2] == str[index] && !set.contains(next2)) {
            HashSet<Integer> branchSet = (HashSet<Integer>) set.clone();
            branchSet.add(next2);
            if (tryPath(matrix, rows, cols, str, branchSet, next2, index + 1)) return true;
        }
        // 往上
        int next3 = pre - cols;
        // 允许向上，元素相等，不能重复
        if (next3 >= 0 && matrix[next3] == str[index] && !set.contains(next3)) {
            HashSet<Integer> branchSet = (HashSet<Integer>) set.clone();
            branchSet.add(next3);
            if (tryPath(matrix, rows, cols, str, branchSet, next3, index + 1)) return true;
        }
        // 往下
        int next4 = pre + cols;
        // 允许向下，元素相等，不能重复
        if (next4 / cols < rows && matrix[next4] == str[index] && !set.contains(next4)) {
            HashSet<Integer> branchSet = (HashSet<Integer>) set.clone();
            branchSet.add(next4);
            if (tryPath(matrix, rows, cols, str, branchSet, next4, index + 1)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "SEE".toCharArray());
    }
}
