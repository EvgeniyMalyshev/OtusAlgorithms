import java.time.Duration;

public class Results {
    Duration insertTime;
    Duration searchTime;
    Duration removeTime;

    public Results() {
    }

    @Override
    public String toString() {
        return
                "Вставка: " + insertTime
                        + "\nПоиск: " + searchTime
                        + "\nУдаление: " + removeTime
                        + "\n";

    }
}