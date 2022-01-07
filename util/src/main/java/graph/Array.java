package graph;

public class Array {

    public static <T> void sort(Comparable<T>[] array) {
        sort(0, array.length - 1, array);
    }

    private static <T> void sort(int left, int right, Comparable<T>[] array) {
        if (left >= right) {
            return;
        }

        final int center = partition(left, right, array);
        sort(left, center - 1, array);
        sort(center + 1, right, array);
    }

    @SuppressWarnings("unchecked")
    private static <T> int partition(int left, int right, Comparable<T>[] array) {
        final T pivot = (T) array[right];
        int a = left - 1;

        for (int m = left; m <= right; m++) {
            if (array[m].compareTo(pivot) <= 0) {
                swap(++a, m, array);
            }
        }

        return a;
    }

    private static <T> void swap(int from, int to, T[] array) {
        final T buffer = array[from];
        array[from] = array[to];
        array[to] = buffer;
    }

    public static <T> void reverse(T[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            swap(i, array.length - i - 1, array);
        }
    }
}
