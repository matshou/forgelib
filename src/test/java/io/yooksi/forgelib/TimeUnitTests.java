package io.yooksi.forgelib;

import io.yooksi.commons.util.MathUtils;
import io.yooksi.forgelib.util.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("WeakerAccess")
public class TimeUnitTests {

    @Test
    public void calculateMinecraftTimeToRealTimeTest() {

        Assertions.assertEquals(0.0138, MathUtils.truncateDecimals(
                TimeUnit.SECONDS.toRealFractionalSeconds(1), 4));

        Assertions.assertEquals(0.83, MathUtils.truncateDecimals(
                TimeUnit.MINUTES.toRealFractionalSeconds(1), 2));

        Assertions.assertEquals(50, TimeUnit.HOURS.toRealSeconds(1));
        Assertions.assertEquals(20, TimeUnit.DAYS.toRealMinutes(1));

        // Translates to 2.33 hours
        Assertions.assertEquals(140, TimeUnit.WEEKS.toRealMinutes(1));
        Assertions.assertEquals(10, TimeUnit.MONTHS.toRealHours(1));

        Assertions.assertEquals(1, Math.round(TimeUnit.SECONDS.toRealSeconds(TimeUnit.getFactor())));
        Assertions.assertEquals(1, TimeUnit.MINUTES.toRealMinutes(TimeUnit.getFactor()));
        Assertions.assertEquals(1, TimeUnit.HOURS.toRealHours(TimeUnit.getFactor()));
        Assertions.assertEquals(1, TimeUnit.DAYS.toRealDays(TimeUnit.getFactor()));
        Assertions.assertEquals(1, TimeUnit.WEEKS.toRealWeeks(TimeUnit.getFactor()));
        Assertions.assertEquals(1, TimeUnit.MONTHS.toRealMonths(TimeUnit.getFactor()));
        Assertions.assertEquals(1, TimeUnit.YEARS.toRealYears(TimeUnit.getFactor()));
    }

    @Test
    public void calculateMinecraftTimeToTicksTest() {

        Assertions.assertEquals(0.27, MathUtils.truncateDecimals(
                TimeUnit.SECONDS.toFractionalTicks(1), 2));

        Assertions.assertEquals(16.6, MathUtils.truncateDecimals(
                TimeUnit.MINUTES.toFractionalTicks(1), 1));

        Assertions.assertEquals(1_000, TimeUnit.HOURS.toTicks(1));
        Assertions.assertEquals(24_000, TimeUnit.DAYS.toTicks(1));
        Assertions.assertEquals(168_000, TimeUnit.WEEKS.toTicks(1));
        Assertions.assertEquals(720_000, TimeUnit.MONTHS.toTicks(1));
        Assertions.assertEquals(8_766_000, TimeUnit.YEARS.toTicks(1));
    }
}
