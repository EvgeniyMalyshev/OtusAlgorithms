import java.time.ZonedDateTime;

class HeapSortTest extends AbstractSortTest {

    @Override
    void runSort(int[] array) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        HeapSort.sort(array);
        System.out.println(TimeCheck.endTrackTime(before, "Heap Sort"));
    }
}
