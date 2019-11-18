package algorithm;

public class StringToInt {

    public static int StrToInt(String str) {
        if (str == null || str.isEmpty() || "+".equals(str) || "-".equals(str)) return 0;
        char[] array = str.toCharArray();
        int pos = array.length;
        int index = 0;
        long num = 0;
        if ('+' == array[0] || '-' == array[0]) {
            pos--;
            index++;
        }
        for (int i = index; i < array.length; i++) {
            if (array[i] < '0' || array[i] > '9') return 0;
            pos--;
            num = num * 10l + (array[i] - 48);
            // 最大值和最小值判断
            if (num > Integer.MAX_VALUE + 1l && '-' == array[0]) return 0;
            if (num > Integer.MAX_VALUE && !('-' == array[0])) return 0;
        }
        if ('-' == array[0]) {
            return (int) (0l - num);
        }
        return (int) num;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 7, 11, 15};
        StrToInt("-2147483648");
    }
}
