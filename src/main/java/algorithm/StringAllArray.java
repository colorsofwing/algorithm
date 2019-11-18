package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class StringAllArray {

    public static ArrayList<String> Permutation(String str) {
        if (str == null || str.isEmpty()) return new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();
        if (str.length() == 1) {
            list.add(str);
            return list;
        }
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 1);
            String param = str.substring(0, i) + str.substring(i + 1, str.length());
            ArrayList<String> templist = Permutation(param);
            for (String substr : templist) {
                list.add(temp + substr);
            }
        }
        ArrayList<String> resultList = new ArrayList<String>(new HashSet<String>(list));
        Collections.sort(resultList);
        return resultList;
    }

    public static void main(String[] args) {
        Permutation("aab");
    }
}
