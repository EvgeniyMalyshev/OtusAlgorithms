import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Benchmark {

	private static final int NUMBER_OF_KEYS = 1_000_000;

	public static void main(String[] args) {
		final int[] keys = new Random().ints(NUMBER_OF_KEYS).toArray();
		final int[] sortedKeys = IntStream.of(keys).sorted().toArray();

		System.out.println("Бинарное дерево неотсортированные ключи\n" + run(new BinarySearchTree(), keys));

		try {
			System.out.println("Бинарное дерево отсортированные ключи\n" + run(new BinarySearchTree(), sortedKeys));
		} catch (StackOverflowError e) {
			System.out.println(e.toString());
		}

		System.out.println("АВЛ случайные ключи\n" + run(new AVLTree(), keys));
		System.out.println("АВЛ сортированные ключи\n" + run(new AVLTree(), sortedKeys));

		System.out.println("Дерево случайные ключи\n" + run(new Tree(), keys));
		System.out.println("Дерево сортированные ключи\n" + run(new Tree(), sortedKeys));
	}

	private static Results run(BinarySearchTree tree, int[] keys) {
		final Results results = new Results();

		ZonedDateTime beforeInsert = TimeCheck.startTrackTime();
		Arrays.stream(keys).forEach(tree::insert);
		results.insertTime = TimeCheck.endTrackTime(beforeInsert);

		ZonedDateTime beforeSearsh = TimeCheck.startTrackTime();
		Arrays.stream(keys).filter(n -> n % 10 == 0).forEach(tree::search);
		results.searchTime = TimeCheck.endTrackTime(beforeSearsh);

		ZonedDateTime beforeRemove = TimeCheck.startTrackTime();
		Arrays.stream(keys).filter(n -> n % 10 == 0).forEach(tree::remove);
		results.removeTime = TimeCheck.endTrackTime(beforeRemove);

		return results;
	}
}
