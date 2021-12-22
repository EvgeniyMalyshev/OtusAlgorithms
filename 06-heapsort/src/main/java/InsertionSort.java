public class InsertionSort {

    static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int buffer = array[i];
            int p = i - 1;

            while (p >= 0 && array[p] > buffer) {
                array[p + 1] = array[p];
                p--;
            }

            array[p + 1] = buffer;
        }

    }

    static void sort(int[] array, int gap) {

        for (int i = 0; i + gap < array.length; i++) {
            int p = i + gap;
            int buffer = array[p];

            while (p - gap >= 0 && array[p - gap] > buffer) {
                array[p] = array[p - gap];
                p -= gap;
            }

            array[p] = buffer;
        }

    }
}
