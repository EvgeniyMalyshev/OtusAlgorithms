import java.time.ZonedDateTime;

class InsertionSortTest extends AbstractSortTest {

	@Override
	void runSort(int[] array) {
		ZonedDateTime before = TimeCheck.startTrackTime();
		InsertionSort.sort(array);
		System.out.println(TimeCheck.endTrackTime(before, "Insertion Sort"));


	}
}
