package io.yooksi.forgelib.error;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.forgelib.ForgeMod;
import net.minecraftforge.fml.common.Mod;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *     Signals that a {@link ForgeMod} implementation is not in an appropriate state for the requested operation.
 *     The class constructor is not publicly accessible so each exception of this type has to be constructed
 *     either through a subclass exception constructor or by using a {@link Cause} creation method.
 * <p>
 *     In other words this exception should never have an ambiguous cause and the introduction of internal
 *     cause enumerations should provide clear causes for those cases when a subclass is not used.
 * </p>
 * @see Cause#create(ForgeMod)
 */
@MethodsNotNull
@SuppressWarnings("WeakerAccess")
public class IllegalModStateException extends RuntimeException {

    /**
     * Represents a reason why this exception occurred.
     * <p>
     *     Each cause needs to provide it's own implementation of creating a new exception that
     *     provides a unique and clear message to inform the user as to why the exception was thrown.
     * </p>
     */
    public enum Cause {

        NOT_ANNOTATED {

            @Override
            public IllegalModStateException create(ForgeMod mod) {

                String message = "is not annotated with " +  Mod.class.getName() + " annotation.";
                return new IllegalModStateException(mod, message);
            }
        };

        /**
         * @param mod {@code ForgeMod} requesting the creation of this exception
         * @return a newly constructed {@code IllegalModStateException} instance for the given mod
         */
        public abstract IllegalModStateException create(ForgeMod mod);
    }

    /**
     * @param mod {@code ForgeMod} constructing this exception
     * @param cause the base message to append additional info to
     * @return a new exception log message that includes additional information
     */
    protected static @NotEmpty String formatMessage(ForgeMod mod, String cause) {

        return mod.getClass().getSimpleName() + " " + cause + " Read " +
                ForgeMod.class.getName() + " class documentation for more information.";
    }

    protected IllegalModStateException(ForgeMod mod, String message) {
        super(formatMessage(mod, message));
    }
}
