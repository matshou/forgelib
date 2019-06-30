package io.yooksi.forgelib.util;

/**
 * <p>
 *     The day-night cycle is a 20 minute long lapse between two main light settings.
 * <p>
 *     In Minecraft time is exactly 72 times faster than normal time. This can be easily calculated as the proportion
 *     <code><sup>1440</sup>/<sub>20</sub> = 72</code>, since there are 1440 minutes in a real day {@code (60min × 24hr)}
 *     and 20 minutes in a full Minecraft day.
 * <p>
 *     A collection of time unit conversions is listed below:
 * </p>
 * <h2>
 *     Minecraft time to real time
 * </h2>
 * <table summary="" border="1">
 *   <tr>
 *        <th>Minecraft time</th> <th>Minecraft ticks</th> <th>Real time</th>
 *   </tr><tr>
 *        <td>1 second</td> <td>0.27</td> <td>0.0138 seconds</td>
 *   </tr><tr>
 *        <td>1 minute</td> <td>16.6</td> <td>0.83 seconds</td>
 *   </tr><tr>
 *        <td>1 hour</td> <td>1000</td> <td>50 seconds</td>
 *   </tr><tr>
 *        <td>1 day</td> <td>24,000</td> <td>20 minutes</td>
 *   </tr><tr>
 *        <td>1 day</td> <td>24,000</td> <td>20 minutes</td>
 *   </tr><tr>
 *       <td>1 week (7 days)</td> <td>168,000</td> <td>2.3 hours</td>
 *   </tr><tr>
 *       <td>1 month (30 days)</td> <td>720,000</td> <td>10 hours</td>
 *   </tr><tr>
 *       <td>1 year (365.25 days)</td> <td>8,766,000</td> <td>121.75 hours (5.072916 days) </td>
 *   </tr>
 * </table>
 * <h2>
 *     Real time to Minecraft time
 * </h2>
 * <p>
 *     The approximation of real time to Minecraft time:
 * </p>
 * <table summary="" border="1">
 *   <tr>
 *       <th>Real time</th> <th>Minecraft time</th>
 *   </tr><tr>
 *       <td>1 tick</td> <td>3.6 Minecraft seconds</td>
 *   </tr><tr>
 *       <td>1 second</td> <td>1 minute and 12 seconds</td>
 *   </tr><tr>
 *       <td>10 seconds</td> <td>12 minutes</td>
 *   </tr><tr>
 *       <td>50 seconds</td> <td>1 hour</td>
 *   </tr><tr>
 *       <td>1 minute</td> <td>1 hour and 12 minutes</td>
 *   </tr><tr>
 *       <td>1 hour</td> <td>3 days</td>
 *   </tr><tr>
 *       <td>1 week</td> <td>≈ 1.385 years, ≈ 17 months, = 72 weeks, = 504 days</td>
 *   </tr><tr>
 *       <td>1 month</td> <td>6 years, = 72 months, ≈ 308.5 weeks, = 2,160 days</td>
 *   </tr><tr>
 *       <td>1 year</td> <td>72 years, ≈ 876.5 months, ≈ 3,757 weeks, ≈ 26,297.5 days</td>
 *   </tr>
 * </table>
 */
public enum TimeUnit {

    SECONDS(1),
    MINUTES(SECONDS.mult * 60),
    HOURS(MINUTES.mult * 60),
    DAYS(HOURS.mult * 24),
    WEEKS(DAYS.mult * 7),          // 7 days
    MONTHS(DAYS.mult * 30),       // 30 days
    YEARS(DAYS.mult * 365.25);   // 365.25 days

    /**
     * Time-factor that makes Minecraft time slower then normal time.
     * Both {@link #tick} and {@link #second} field values were calculated using this factor.
     * You can manually use this value to convert normal time to Minecraft time and vice-versa.
     */
    private static final int factor = 72;
    /**
     * Precise system tick fraction that elapses with each Minecraft second.
     */
    private static final double tick = 0.2777777777777778;
    /**
     * Precise real-time second fraction that elapses with each system tick.
     */
    private static final double second = 0.0138888888888889;

    private final double mult;
    private final double ticks;
    private final double seconds;

    TimeUnit(double mult) {

        this.mult = mult;
        this.ticks = tick * mult;
        this.seconds = second * mult;
    }

    public static int getFactor() {
        return factor;
    }

    /**
     * Convert the given time duration for the current {@code TimeUnit} to system ticks.
     * @return a representation of real ticks elapsed in the given time duration.
     *         The value is rounded down to the largest {@code long} that is less
     *         than or equal to the calculated value.
     */
    public long toTicks(long duration) {
        return (long) Math.floor(ticks * duration);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to fractional system ticks.
     * Use this method when you require a more precise representation of elapsed ticks.
     */
    public double toFractionalTicks(long duration) {
        return ticks * duration;
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time seconds.
     * @return a representation of real seconds elapsed in the given time duration.
     *         The value is rounded down to the largest {@code long} that is less
     *         than or equal to the calculated value.
     */
    public long toRealSeconds(long duration) {
        return Math.round(seconds * duration);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to fractions of real-time seconds.
     * The returned value is a {@code double} as a basis for manually measuring other units.
     * Use this method when you require a more precise representation of elapsed ticks.
     */
    public double toRealFractionalSeconds(long duration) {
        return seconds * duration;
    }

    private long convert(java.util.concurrent.TimeUnit unit, long duration) {
        return unit.convert(Math.round(seconds * duration), java.util.concurrent.TimeUnit.SECONDS);
    }

    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time minutes.
     */
    public long toRealMinutes(long duration) {
        return convert(java.util.concurrent.TimeUnit.MINUTES, duration);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time hours.
     */
    public long toRealHours(long duration) {
        return convert(java.util.concurrent.TimeUnit.HOURS, duration);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time days.
     */
    public long toRealDays(long duration) {
        return convert(java.util.concurrent.TimeUnit.DAYS, duration);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time weeks.
     */
    public long toRealWeeks(long duration) {
        return Math.round(toRealDays(duration) / 7);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time months.
     */
    public long toRealMonths(long duration) {
        return Math.round(toRealDays(duration) / 30);
    }
    /**
     * Convert the given time duration for the current {@code TimeUnit} to real-time years.
     */
    public long toRealYears(long duration) {
        return Math.round(toRealMonths(duration) / 12);
    }
}
