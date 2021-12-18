package test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class FileGenerator {

    public static void print(File file) throws IOException {
        try (final RandomAccessFile accessFile = new RandomAccessFile(file, "r")) {
            while (accessFile.getFilePointer() < accessFile.length()) {
                System.out.println(accessFile.readUnsignedShort());
            }
        }
    }

    public static void generate(int numbersSize, int maxValue, File file) throws IOException {
        final Random random = new Random();

        try (final RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < numbersSize; i++) {
                accessFile.writeShort(random.nextInt(maxValue));
            }
        }
    }
}
