package io.yooksi.forgelib.logger;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.logger.CommonLogger;
import io.yooksi.commons.logger.LoggerLevels;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.spi.StandardLevel;

/**
 * Wrapper class for Apache Log4j logging system.
 * Use this for all mod logging purposes.
 */
@SuppressWarnings("unused")
@MethodsNotNull
public class ModLogger extends CommonLogger {

    private final boolean debug;

    public ModLogger(String logger, boolean dedicatedFile)
    {
        // TODO: Move the property getter to a dedicated method for reusability
        super(logger, Level.toLevel(System.getProperty("modLoggerLevel"), Level.INFO),
                dedicatedFile, false, true);

        debug = getLevel(LoggerLevels.Type.CONSOLE).intLevel() >= StandardLevel.DEBUG.intLevel();
    }

    /**
     * @return {@code true} if the current session is running in debug mode.
     */
    public boolean isDebug() {
        return debug;
    }
}
