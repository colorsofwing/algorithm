package algorithm;

public class JudgeNumber {

    public static boolean isNumeric(char[] str) {
        if(str == null || str.length == 0)return false;
        int start = 0;
        int end = str.length - 1;
        boolean dot = false;
        int index = 0;
        if(str[start] == '+' || str[start] == '-') {
            start++;
            index++;
        }
        for(int i = start ;i <= end;i++){
            if(!dot && str[i] == '.'){
                dot=true;
                if(isNatureNumber(str,i+1,end)){
                    return true;
                }else{
                    index=i+1;
                    continue;
                }
            }
            if(str[i] == 'e' || str[i] == 'E' ){
                if(i + 1 <= end && (str[i + 1] == '-' ||str[i + 1] == '+')){
                    return isNatureNumber(str,index,i-1) && isNatureNumber(str,i+2,end);
                }else{
                    return isNatureNumber(str,index,i-1) && isNatureNumber(str,i+1,end);
                }
            }
            if(str[i] < '0' || str[i] > '9') return false;
        }
        return true;
    }

    public static boolean isNatureNumber(char[] str, int start, int end){
        if(str == null || str.length == 0 || start > end)return false;
        for(int i = start;i<=end;i++){
            if(str[i] < '0' || str[i] > '9') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        isNumeric("-1E-16".toCharArray());
    }
}
