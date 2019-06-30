package io.yooksi.forgelib.meta;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.define.RegExPatterns;
import io.yooksi.forgelib.ForgeMod;
import io.yooksi.forgelib.error.MalformedMetadataException;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * This class represents {@link ForgeMod} metadata information stored in Forge {@link Mod} annotation.
 * Each metadata type can be constructed by invoking a type-specific metadata creation method.
 * The metadata information will then be validated inside the constructor using a type-specific
 * <i>regular expression</i>. Read method documentation for more information.
 *
 * @see Type#create(String, ForgeMod)
 */
@MethodsNotNull
@SuppressWarnings("WeakerAccess")
public class Metadata {

    /**
     * The regular expression patterns for each type are defined as constants.
     */
    public enum Type {

        NAME(null),
        MOD_ID(RegExPatterns.UNIX_NAMING_CONVENTION),
        VERSION(RegExPatterns.SIMPLE_VERSION_NUMBER) {

            @Override
            @Contract("_, _ -> new")
            @SuppressWarnings("unchecked")
            public ModVersion create(String value, ForgeMod mod) {
                return new ModVersion(value, mod);
            }
        };

        private final @Nullable java.util.regex.Pattern pattern;

        Type(@Nullable java.util.regex.Pattern pattern) {
            this.pattern = pattern;
        }

        /**
         * Construct and validated a new {@code Metadata} of this type.
         * <p>Note that the validation is done inside the constructor itself.
         *
         * @param value actual value the metadata will hold
         * @param mod owner of the metadata we are creating
         * @param <T> ensures method overriding compatibility
         * @return newly constructed and validated {@code Metadata} instance
         *
         * @throws MalformedMetadataException if the metadata value failed to match the regular
         *                                    expression pattern associated with this metadata type.
         *
         * @see Metadata#validate(ForgeMod)
         */
        @Contract("_, _ -> new")
        @SuppressWarnings("unchecked")
        public <T extends Metadata> T create(String value, ForgeMod mod) {
            return (T) new Metadata(this, value, mod);
        }
    }

    protected final Type type;
    protected final String value;

    /**
     * This constructor cannot be invoked from public scope.
     * Instead {@link Type#create(String, ForgeMod)} method is used to
     * provide a more convenient way to construct metadata for specific types.
     *
     * @param type {@code Type} this metadata identifies with
     * @param value actual value the metadata will hold
     * @param mod owner of the metadata we are creating
     *
     * @throws MalformedMetadataException if the metadata value failed to match the regular
     *                                    expression pattern associated with this metadata type.
     *
     * @see Metadata#validate(ForgeMod)
     */
    protected Metadata(Type type, String value, ForgeMod mod) {

        this.type = type;
        this.value = value;
        validate(mod);
    }

    /**
     * Validate the metadata for the given mod using a type-specific <i>regular expression</i>.
     *
     * @param mod owner of this metadata whose class name is used to construct
     *            the exception message if the metadata value is invalid.
     *
     * @throws MalformedMetadataException if the metadata value failed to match the regular
     *                                    expression pattern associated with this metadata type.
     */
    protected void validate(ForgeMod mod) throws MalformedMetadataException {

        if (type.pattern != null && !type.pattern.matcher(value).find()) {
            throw new MalformedMetadataException(mod, this);
        }
    }

    /**
     * @return the {@code String} representation of the value this metadata holds.
     *         This method returns a different value then {@link #toString()} that
     *         provides type and value information in a <i>print-ready</i> format.
     */
    @Contract(pure = true)
    public String getValue() {
        return value;
    }

    /**
     * @return {@code Type} and value information in a <i>print-ready</i> format.
     */
    @Override
    public @NotEmpty String toString() {
        return type.name() + ": " + value;
    }
}