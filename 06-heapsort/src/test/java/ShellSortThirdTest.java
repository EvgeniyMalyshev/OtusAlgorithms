import java.time.ZonedDateTime;

class ShellSortThirdTest extends AbstractSortTest {

	@Override
	void runSort(int[] array) {
		ZonedDateTime before = TimeCheck.startTrackTime();
		ShellSort.shellThirdSequence(array);
		System.out.println(TimeCheck.endTrackTime(before, "Shell Sort 3"));
	}
}
