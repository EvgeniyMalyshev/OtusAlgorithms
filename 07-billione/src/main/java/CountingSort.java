import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CountingSort {

    public static final double NUMBER_OF_TWO_BYTE_NUMBERS = Math.pow(2, 16);

    public static void sort(File inputFile, File outputFile) throws IOException {
        final long[] sums = new long[(int) NUMBER_OF_TWO_BYTE_NUMBERS];

        try (final RandomAccessFile accessInputFile = new RandomAccessFile(inputFile, "r")) {
            while (accessInputFile.getFilePointer() < inputFile.length()) {
                sums[accessInputFile.readUnsignedShort()] += 1;
            }

            for (int i = 1; i < sums.length; i++) {
                sums[i] = sums[i] + sums[i - 1];
            }

            try (final RandomAccessFile accessOutputFile = new RandomAccessFile(outputFile, "rw")) {
                for (long position = inputFile.length() - 2; position >= 0; position -= 2) {
                    accessInputFile.seek(position);
                    final int number = accessInputFile.readUnsignedShort();
                    sums[number] -= 1;
                    accessOutputFile.seek(sums[number] << 1);
                    accessOutputFile.writeShort(number);
                }
            }
        }
    }
}
