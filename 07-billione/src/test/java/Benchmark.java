import test.FileGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.util.UUID;


public class Benchmark {

	public static final int[] NUMBER_SIZE = {(int) 1e6, (int) 1e7, (int) 1e8, (int) 1e9};
	private static final int MAX_VALUE = (int) Math.pow(2, 16) - 1;

	public static void main(String[] args) throws IOException {
		for (int size : NUMBER_SIZE) {
			runMergeSort(size);
			runCountingSort(size);
		}
	}

	private static void runMergeSort(int numbersSize) throws IOException {
		System.out.println("Run Merge Sort on file with " + numbersSize + " numbers");

		final File file = new File(UUID.randomUUID().toString());
		FileGenerator.generate(numbersSize, MAX_VALUE, file);

		ZonedDateTime before = TimeCheck.startTrackTime();
		MergeSort.sort(file);
		System.out.println(TimeCheck.endTrackTime(before, "Merge Sort"));

		Files.deleteIfExists(file.toPath());
	}

	private static void runCountingSort(int numbersSize) throws IOException {
		System.out.println("Run Counting Sort on file with " + numbersSize + " numbers");

		final File inputFile = new File(UUID.randomUUID().toString());
		final File outputFile = new File(UUID.randomUUID().toString());
		FileGenerator.generate(numbersSize, MAX_VALUE, inputFile);

		ZonedDateTime before = TimeCheck.startTrackTime();
		CountingSort.sort(inputFile, outputFile);
		System.out.println(TimeCheck.endTrackTime(before, "Count Sort"));

		Files.deleteIfExists(inputFile.toPath());
		Files.deleteIfExists(outputFile.toPath());
	}
}
