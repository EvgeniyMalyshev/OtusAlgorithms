import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class MergeSort {

    private static final int MAX_SIZE_FOR_IN_MEMORY_SORTING = 10_000;

    public static void sort(File file) throws IOException {
        try (final RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
            sort(0, accessFile.length() / 2 - 1, accessFile);
        }
    }

    private static void sort(long left, long right, RandomAccessFile file) throws IOException {
        if (left >= right) {
            return;
        }

        if (right - left < MAX_SIZE_FOR_IN_MEMORY_SORTING) {
            sortInMemory(left, right, file);
            return;
        }

        final long pivot = (left + right) / 2;

        sort(left, pivot, file);
        sort(pivot + 1, right, file);
        merge(left, pivot, right, file);
    }

    private static void sortInMemory(long left, long right, RandomAccessFile file) throws IOException {
        final int[] array = new int[(int) (right - left) + 1];
        file.seek(left * 2);
        for (int i = 0; i < array.length; i++) {
            array[i] = file.readUnsignedShort();
        }
        QuickSort.sort(array);
        file.seek(left * 2);
        for (int value : array) {
            file.writeShort(value);
        }
    }

    private static void merge(long left, long pivot, long right, RandomAccessFile file) throws IOException {
        long l = left;
        long r = pivot + 1;
        final String mergeFilename = left + "_" + pivot + "_" + right;

        try (final RandomAccessFile mergeFile = new RandomAccessFile(mergeFilename, "rw")) {
            while (l <= pivot && r <= right) {
                file.seek(l * 2);
                final int leftItem = file.readUnsignedShort();
                file.seek(r * 2);
                final int rightItem = file.readUnsignedShort();

                if (leftItem < rightItem) {
                    mergeFile.writeShort(leftItem);
                    l++;
                } else {
                    mergeFile.writeShort(rightItem);
                    r++;
                }
            }

            while (l <= pivot) {
                file.seek(l++ * 2);
                mergeFile.writeShort(file.readUnsignedShort());
            }

            while (r <= right) {
                file.seek(r++ * 2);
                mergeFile.writeShort(file.readUnsignedShort());
            }

            for (long i = left; i <= right; i++) {
                file.seek(i * 2);
                mergeFile.seek((i - left) * 2);
                file.writeShort(mergeFile.readUnsignedShort());
            }
        }

        Files.deleteIfExists(Path.of(mergeFilename));
    }
}
