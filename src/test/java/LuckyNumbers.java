import org.junit.Assert;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class LuckyNumbers {

    @org.junit.Test
    public void LengthTest(){
       String test = "qwerty";
       int size = test.length();
        Assert.assertEquals(6,size);
    }

    @org.junit.Test
    public void TicketTest(){
        int n = 2;
        LinkedHashSet<Integer> hashSet = getNum(n);
        Assert.assertTrue(hashSet.contains(670));

    }

    private LinkedHashSet<Integer> getNum(int number){
        int[] firstArray = getFirstArray(number);

        LinkedHashSet<Integer> resultSet = new LinkedHashSet<>();

            int numberOfCircle = (number/2-1) == 0
                    ? 1
                    : number/2-1;
            int [] modyfiedArray =  firstArray;
            for (int i = 1; i <=numberOfCircle; i++) {
                int result = 0;

                int [] array = getInts(modyfiedArray);
                for (int value : array) {
                    result += value;
                }
                resultSet.add(result);
                modyfiedArray = array;
            }
            return resultSet;
        }



    private int[] getInts(int[] preveusArray) {
            int length = preveusArray.length + 9;
            int[] newArray = new int[length];
            for (int i = 0; i <length; i++) {
                int summ = 0;
                for (int j = 0; j <10; j++) {
                    if ((i-j)>0 && (i-j)< preveusArray.length){
                        summ+=preveusArray[i-j];
                    }
                }
                newArray[i] = summ;
            }
            return newArray;
        }

    private int[] getFirstArray(int number) {
        int [] firstArray = new int[number *9];
        for (int i = 0; i < number *9; i++) {
            if (i<10){
                firstArray[i] = 1;
            }else firstArray[i] = 0;
        }
        return firstArray;
    }
    }



