public class SelectionSort {

    public static void sort(int[] array) {
        final int size = array.length;

        for (int i = 0; i < size - 1; i++) {
            int minItemIndex = i;

            for (int j = i + 1; j < size; j++) {
                if (array[minItemIndex] > array[j]) {
                    minItemIndex = j;
                }
            }

            if (minItemIndex != i) {
                swap(i, minItemIndex, array);
            }
        }
    }

    private static void swap(int from, int to, int[] array) {
        final int buffer = array[from];
        array[from] = array[to];
        array[to] = buffer;
    }
}
