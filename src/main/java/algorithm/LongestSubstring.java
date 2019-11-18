package algorithm;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] array = s.toCharArray();
        int len = array.length;
        int[] pos = new int[128];
        int j = 0;
        while (j < pos.length) {
            pos[j] = -1;
            j++;
        }
        int head = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (pos[array[i]] < i) {
                head = pos[array[i]] >= head ? pos[array[i]] + 1 : head;
                pos[array[i]] = i;
            }
            if (i - head + 1 > max) max = i - head + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("pwwkew");
    }
}
