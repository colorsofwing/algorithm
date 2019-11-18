package algorithm;

public class FrequencyOne {

    public static int NumberOf1Between1AndN_Solution(int n) {
        String str = Integer.toString(n);
        int result = 0;
        int length = str.length();
        for (char c : str.toCharArray()) {
            if (c > '0') {
                int residue = n % (int) Math.pow(10, length - 1);
                result += fetchFrequency(c, length, residue);
            }
            length--;
        }
        return result;
    }

    private static int fetchFrequency(char c, int pos, int residue) {
        int num = c - 48;
        if (pos == 1) return 1;
        if (num == 1) return fetchBaseNum(pos - 1) + residue + 1;
        return num * fetchBaseNum(pos - 1) + (int) Math.pow(10, pos - 1);
    }

    private static int fetchBaseNum(int length) {
        if (length < 1) return 0;
        if (length == 1) return 1;
        return 10 * (fetchBaseNum(length - 1) + (int) Math.pow(10, length - 2));
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(10));
    }
}
