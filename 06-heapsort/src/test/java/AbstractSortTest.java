import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import test.TestData;
import test.TestDataSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public abstract class AbstractSortTest {

	private static String generateDisplayName(TestData testData) {
		return "with length " + testData.getInput().get(0);
	}

	private static int[] toArray(String string) {
		return Arrays.stream(string.split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	@TestFactory
	Stream<DynamicTest> testWithRandomArray() {
		System.out.println("RandomArray");
		return DynamicTest.stream(
				new TestDataSource("0.random"),
				AbstractSortTest::generateDisplayName,
				this::executeTest
		);
	}

	@TestFactory
	Stream<DynamicTest> testWithDigitsArray() {
		System.out.println("DigitsArray");
		return DynamicTest.stream(
				new TestDataSource("1.digits"),
				AbstractSortTest::generateDisplayName,
				this::executeTest
		);
	}

	@TestFactory
	Stream<DynamicTest> testWithSortedArray() {
		System.out.println("SortedArray");
		return DynamicTest.stream(
				new TestDataSource("2.sorted"),
				AbstractSortTest::generateDisplayName,
				this::executeTest
		);
	}

	@TestFactory
	Stream<DynamicTest> testWithReversedArray() {
		System.out.println("ReversedArray");
		return DynamicTest.stream(
				new TestDataSource("3.revers"),
				AbstractSortTest::generateDisplayName,
				this::executeTest
		);
	}

	private void executeTest(TestData testData) {
		final int[] expectedResult = toArray(testData.getOutput().get(0));
		final int[] array = toArray(testData.getInput().get(1));

		runSort(array);

		assertArrayEquals(expectedResult, array);
	}

	abstract void runSort(int[] array);

}
