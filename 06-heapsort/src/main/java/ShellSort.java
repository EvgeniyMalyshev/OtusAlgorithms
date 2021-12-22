public class ShellSort {

    public static void shellSequence(int[] array) {

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            InsertionSort.sort(array, gap);
        }

    }

    public static void shellSecondSequence(int[] array) {

        final int size = array.length;
        int k = 0;

        while (Math.pow(2, k) - 1 < size) {
            k++;
        }

        while (k > 0) {
            InsertionSort.sort(array, (int) Math.pow(2, k) - 1);
            k--;
        }
    }

    public static void shellThirdSequence(int[] array) {

        final int size = array.length;

        int gap = 1;
        while (gap < size / 3) {
            gap = 3 * gap + 1;
        }

        while (gap >= 1) {

            InsertionSort.sort(array, gap);
            gap /= 3;
        }

    }
}
