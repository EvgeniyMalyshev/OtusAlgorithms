import arrays.list.FactorArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;

public class List {

    private final FactorArrayList<Integer> factorArrayList = new FactorArrayList<>();

    @Test
    public void testList() {
        factorArrayList.add(1);
        factorArrayList.add(2);
        factorArrayList.add(3);
        factorArrayList.add(4);
        factorArrayList.add(5);
        Assert.assertEquals(5, factorArrayList.size());
        Assert.assertEquals(Integer.valueOf(5), factorArrayList.get(factorArrayList.size() - 1));
        Assert.assertEquals(Integer.valueOf(3), factorArrayList.get(2));
        Assert.assertEquals(Integer.valueOf(3), factorArrayList.remove(2));
        Assert.assertEquals(4, factorArrayList.size());

        System.out.println(fillList(factorArrayList, 16));
    }

    private String fillList(FactorArrayList<Integer> factorArrayList, int number) {
        ZonedDateTime before = TimeCheck.startTrackTime();
        for (int i = 0; i < number; i++) {
            factorArrayList.add(i);
        }
        return TimeCheck.endTrackTime(before, "Время заполнения list");
    }
}
