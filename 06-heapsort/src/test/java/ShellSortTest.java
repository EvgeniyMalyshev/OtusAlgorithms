import java.time.ZonedDateTime;

class ShellSortTest extends AbstractSortTest {

	@Override
	void runSort(int[] array) {
		ZonedDateTime before = TimeCheck.startTrackTime();
		ShellSort.shellSequence(array);
		System.out.println(TimeCheck.endTrackTime(before, "Shell Sort"));
	}
}
