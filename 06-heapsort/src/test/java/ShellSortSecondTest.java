import java.time.ZonedDateTime;


class ShellSortSecondTest extends AbstractSortTest {

	@Override
	void runSort(int[] array) {
		ZonedDateTime before = TimeCheck.startTrackTime();
		ShellSort.shellSecondSequence(array);
		System.out.println(TimeCheck.endTrackTime(before, "Shell Sort 2"));

	}
}
