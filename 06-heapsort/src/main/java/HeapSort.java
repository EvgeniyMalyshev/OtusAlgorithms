public class HeapSort {

    public static void sort(int[] array) {
        final int size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            recoverHeap(i, size, array);
        }

        for (int j = size - 1; j >= 0; j--) {
            swap(0, j, array);
            recoverHeap(0, j, array);
        }
    }

    private static void recoverHeap(int root, int size, int[] array) {
        final int left = 2 * root + 1;
        final int right = 2 * root + 2;
        int max = root;

        if (left < size && array[max] < array[left]) {
            max = left;
        }

        if (right < size && array[max] < array[right]) {
            max = right;
        }

        if (max == root) {
            return;
        }

        swap(root, max, array);
        recoverHeap(max, size, array);
    }

    private static void swap(int from, int to, int[] array) {
        final int buffer = array[from];
        array[from] = array[to];
        array[to] = buffer;
    }
}
