import java.util.HashSet;
import java.util.LinkedHashSet;

public class Util {
    public HashSet getNum(int number){
        int [] firstArray = new int[number*9];
        for (int i = 0; i < number*9; i++) {
            if (i<10){
                firstArray[i] = 1;
            }else firstArray[i] = 0;
        }
        LinkedHashSet<Integer> resultSet = new LinkedHashSet<>();
        for (int i = 0; i <(number/2-1); i++) {
            int result = 0;
            int [] array = getInts(firstArray);
            for (int value : array) {
                result += value;
            }
            resultSet.add(result);
        }
        return resultSet;
    }


    private int[] getInts(int[] preveusArray) {
        int length = preveusArray.length + 9;
        int[] newArray = new int[preveusArray.length];
        for (int i = 0; i < length; i++) {
            int summ = 0;
            for (int j = 0; j < 10; j++) {
                summ += preveusArray[i-j];
            }
            newArray[i] = summ;
        }
        return newArray;
    }
}
