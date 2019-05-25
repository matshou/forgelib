package io.yooksi.forgelib.logger;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.logger.CommonLogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.spi.StandardLevel;

/**
 * Wrapper class for Apache Log4j logging system.
 * Use this for all mod logging purposes.
 */
@SuppressWarnings("unused")
@MethodsNotNull
public class ModLogger extends CommonLogger {

    private boolean debug;

    public ModLogger(String logger, boolean dedicatedFile)
    {
        super(logger, Level.toLevel(System.getProperty("modLoggerLevel"), Level.INFO),
                dedicatedFile, false, true);

        debug = getLogLevel().intLevel() >= StandardLevel.DEBUG.intLevel();
    }

    /**
     * @return {@code true} if the current session is running in debug mode.
     */
    public boolean isDebug() {
        return debug;
    }
}
