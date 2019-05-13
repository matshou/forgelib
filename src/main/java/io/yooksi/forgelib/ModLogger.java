package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.logger.CommonLogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;

/**
 * Wrapper class for Apache Log4j logging system.
 * Use this for all mod logging purposes.<br>
 *
 * @see ModLogger.DebugMode
 */
@SuppressWarnings("unused")
@MethodsNotNull
public class ModLogger extends CommonLogger {

    /**
     * A data type representing which debug mode our session is using.
     * <ul>
     *     <li><b>STANDARD</b>: Print <i>only</i> mod debug logs to console</li>
     *     <li><b>VERBOSE</b>: Print both mod and Forge logs to console</li>
     *     <li><b>UNKNOWN</b>: Debug mode was not recognized</li>
     * </ul>
     */
    public enum DebugMode {

        STANDARD("standard"),
        VERBOSE("verbose"),
        UNKNOWN("");

        private static DebugMode mode;
        private final String name;

        DebugMode(String mode) {
            this.name = mode;
        }
        static boolean init() {
            return (mode = findFromSysProperties()) != null;
        }
        static @Nullable DebugMode findFromSysProperties() {

            String sMode = System.getProperty("debug.mode");
            if (sMode == null) return null;

            for (DebugMode eMode : DebugMode.values()) {
                if (eMode.name.equalsIgnoreCase(sMode))
                    return eMode;
            }
            return UNKNOWN;
        }
        static boolean is(DebugMode mode) {
            return DebugMode.mode == mode;
        }
    }
    /**
     * @return {@code true} if the current session is running in debug mode.
     */
    public static boolean isDebug() {
        return DebugMode.mode != null && !DebugMode.is(DebugMode.UNKNOWN);
    }

    public static ModLogger create(String modId) {

        /*for (Annotation anno : modClass.getDeclaredAnnotations()) {
            if (!anno.annotationType().getName().equals("net.minecraftforge.fml.common.Mod")) {
                throw new IllegalStateException();
            }
        }*/

        ModLogger logger = CommonLogger.create(modId, ModLogger.class);

        if (DebugMode.init()) {
            /*
             * This will enable Forge and mod Log4j debug logs to be printed to
             * console in addition to the debug logfile.
             */
            if (DebugMode.is(DebugMode.VERBOSE))
            {
                LoggerContext context = ((org.apache.logging.log4j.core.Logger) logger.get()).getContext();
                LoggerConfig logConfig = context.getConfiguration().getRootLogger();
                Appender consoleAppender = logConfig.getAppenders().get("Console");
                logConfig.removeAppender("Console");
                logConfig.addAppender(consoleAppender, Level.DEBUG, null);
            }
            else if (DebugMode.is(DebugMode.UNKNOWN))
                logger.warn("Unknown debug mode passed as VM argument");
        }
        return logger;
    }
    /** Print debug log to console and mod logfile */
    public void debug(String log) {

        super.debug(log);
        if (DebugMode.is(DebugMode.STANDARD))
            super.info("DEBUG: " + log);

    }
    /** Print debug log to console and mod logfile */
    public void debug(String format, Object...args) {

        super.printf(Level.DEBUG, format, args);
        if (DebugMode.is(DebugMode.STANDARD))
            super.printf(Level.INFO, "DEBUG: " + format, args);

    }
    /** Print debug log to console and mod logfile */
    public void debug(String log, Throwable e) {

        super.debug(log, e);
        if (DebugMode.is(DebugMode.STANDARD))
            super.info("DEBUG: " + log, e);
    }
}
