package algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Continuous {

    public static boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length == 0) return false;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int zero = 0;
        for(int num:numbers){
            if(num == 0){
                zero++;
            }else {
                list.add(new Integer(num));
            }
        }
        if(list.isEmpty() || list.size() == 1) return true;
        Collections.sort(list);
        Integer index = null;
        for(Integer num:list){
            if(index == null){
                index = num;
                continue;
            }
            while (num != index + 1){
                if(zero == 0) return false;
                index++;
                zero--;
            }
            index = num;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,3,2,6,4};
        isContinuous(arr);
    }
}
