import arrays.array.*;
import org.junit.Test;

import java.time.ZonedDateTime;

public class Arrays {


    @Test
    public void testArray() {
        System.out.println(singleArrayTestFill());
        System.out.println(factorArrayTestFill());
        System.out.println(matrixArrayTestFill());
        System.out.println(vectorArrayTestFill());

        System.out.println(singleArrayTestClean());
        System.out.println(factorArrayTestClean());
        System.out.println(matrixArrayTestClean());
        System.out.println(vectorArrayTestClean());
    }

    private String singleArrayTestFill() {
        DynamicArray<Integer> array = new SingleArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения single array");
    }

    private String singleArrayTestClean() {
        DynamicArray<Integer> array = new SingleArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки single array");
    }

    private String factorArrayTestFill() {
        DynamicArray<Integer> array = new FactorArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения factor array");
    }

    private String factorArrayTestClean() {
        DynamicArray<Integer> array = new FactorArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки factor array");
    }

    private String matrixArrayTestFill() {
        DynamicArray<Integer> array = new MatrixArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения matrix array");
    }

    private String matrixArrayTestClean() {
        DynamicArray<Integer> array = new MatrixArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки matrix array");
    }

    private String vectorArrayTestFill() {
        DynamicArray<Integer> array = new VectorArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения vector array");
    }

    private String vectorArrayTestClean() {
        DynamicArray<Integer> array = new VectorArray<>();
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки vector array");
    }

    private DynamicArray<Integer> fillArray(DynamicArray<Integer> integerDynamicArray, int number) {
        for (int i = 0; i < integerDynamicArray.size(); i++) {
            integerDynamicArray.add(number, i);
        }
        return integerDynamicArray;
    }

    private DynamicArray<Integer> cleanArray(DynamicArray<Integer> integerDynamicArray) {
        for (int i = 0; i < integerDynamicArray.size(); i++) {
            integerDynamicArray.remove(i);
        }
        return integerDynamicArray;
    }


}
