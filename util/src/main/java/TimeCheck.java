import java.time.Duration;
import java.time.ZonedDateTime;

public class TimeCheck {
    public static ZonedDateTime startTrackTime() {
        return ZonedDateTime.now();
    }

    public static String endTrackTime(ZonedDateTime before, String description) {
        ZonedDateTime after = ZonedDateTime.now();
        Duration duration = Duration.between(before, after);
        return description + " " + duration;
    }

    public static Duration endTrackTime(ZonedDateTime before) {
        ZonedDateTime after = ZonedDateTime.now();
        return Duration.between(before, after);
    }
}
