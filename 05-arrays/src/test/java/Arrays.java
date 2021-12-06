import arrays.array.*;
import org.junit.Test;

import java.time.ZonedDateTime;

public class Arrays {
    private final DynamicArray<Integer> vectorArray = new VectorArray<>();
    private final DynamicArray<Integer> singleArray = new SingleArray<>();
    private final DynamicArray<Integer> factorArray = new FactorArray<>();
    private final DynamicArray<Integer> matrixArray = new MatrixArray<>();

    @Test
    public void testArray() {
        System.out.println(singleArrayTestFill(singleArray));
        System.out.println(factorArrayTestFill(factorArray));
        System.out.println(matrixArrayTestFill(matrixArray));
        System.out.println(vectorArrayTestFill(vectorArray));

        System.out.println(singleArrayTestClean(singleArray));
        System.out.println(factorArrayTestClean(factorArray));
        System.out.println(matrixArrayTestClean(matrixArray));
        System.out.println(vectorArrayTestClean(vectorArray));
    }

    private String singleArrayTestFill(DynamicArray<Integer> singleArray) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(singleArray, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения single array");
    }

    private String singleArrayTestClean(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки single array");
    }

    private String factorArrayTestFill(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения factor array");
    }

    private String factorArrayTestClean(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки factor array");
    }

    private String matrixArrayTestFill(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения matrix array");
    }

    private String matrixArrayTestClean(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        cleanArray(array);
        return TimeCheck.endTrackTime(before, "Время очистки matrix array");
    }

    private String vectorArrayTestFill(DynamicArray<Integer> array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        fillArray(array, 100);
        return TimeCheck.endTrackTime(before, "Время заполнения vector array");
    }

    private String vectorArrayTestClean(DynamicArray<Integer> array) {
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
