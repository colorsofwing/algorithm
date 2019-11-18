package algorithm;

import java.util.HashMap;

public class FindCharacter {

    public static int FirstNotRepeatingChar(String str) {
        if(str == null || str.isEmpty())return -1;
        char[] array = str.toCharArray();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>(256);
        Integer i = 0;
        for(char letter : array){
            Character cha = new Character(letter);
            if(map.get(cha) == null){
                map.put(cha,new Integer(i));
            }else if(!map.get(cha).equals(new Integer(-1))){
                map.put(cha,new Integer(-1));
            }
            i = i + 1;
        }
        int min = 10000;
        for(Character key: map.keySet()){
            if(!map.get(key).equals(new Integer(-1)) && map.get(key) < min) min = map.get(key).intValue();
        }
        return min;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar("google");
    }
}
