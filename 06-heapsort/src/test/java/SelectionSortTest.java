import java.time.ZonedDateTime;


class SelectionSortTest extends AbstractSortTest {

    @Override
    void runSort(int[] array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        SelectionSort.sort(array);
        System.out.println(TimeCheck.endTrackTime(before, "Selection Sort"));
    }
}
