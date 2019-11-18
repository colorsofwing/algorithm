package algorithm;

public class Regex {

    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;
        if (str.length == 0) {
            if (pattern.length == 0 || (pattern.length == 2 && pattern[1] == '*')) {
                return true;
            } else {
                return false;
            }
        }
        if (pattern.length == 0) return false;
        int i = 0;
        int j = 0;
        while (i < str.length) {
            if (j == pattern.length - 1) {
                if (i == str.length - 1 && (str[i] == pattern[j] || pattern[j] == '.')) {
                    return true;
                } else {
                    return false;
                }
            }
            if (j > pattern.length - 1) return false;
            if (pattern[j + 1] == '*') {
                int index = i;
                // 记录回退数量，管理这样的"a*a"的匹配
                int back = 0;
                int point = j + 1;
                while (++point <= pattern.length - 1 && ((index + 1 < str.length && str[index + 1] == pattern[j]) || index + 1 == str.length)) {
                    if (pattern[point] == pattern[j]) {
                        back++;
                        if (point + 1 <= pattern.length - 1 && pattern[point] == '*') back--;
                    }
                }
                // 单独处理"."的回退问题
                while (++point <= pattern.length - 1 && pattern[j] == '.') {
                    back++;
                    if (point + 1 <= pattern.length - 1 && pattern[point] == '*') back--;
                }
                while (index < str.length && (str[index] == pattern[j] || pattern[j] == '.')) {
                    index++;
                }
                index -= back;
                if (index == str.length && j + 1 == pattern.length - 1) return true;
                if (j + 1 == pattern.length - 1) return false;
                if (index == str.length) return false;
                j += 2;
                i = index;
            } else {
                if (str[i] != pattern[j] && pattern[j] != '.') return false;
                i++;
                j++;
            }
        }
        if (j + 1 == pattern.length - 1 && pattern[j + 1] == '*') return true;
        return false;
    }

    public static void main(String[] args) {
        match("bbbba".toCharArray(), ".*a*a".toCharArray());
        match("aaa".toCharArray(), "ab*a*c*a".toCharArray());
        match("aaca".toCharArray(), "ab*a*c*a".toCharArray());
        match("ab".toCharArray(), ".*".toCharArray());
    }
}
