package io.yooksi.forgelib.meta;

import io.yooksi.forgelib.ForgeMod;
import net.minecraftforge.fml.common.Mod;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

/**
 * <p>
 *     This class represents {@link ForgeMod} version metadata information stored in
 *     {@link Mod#version()}and provides getter methods to read specific version groups
 *     such as {@code MajorVersion} and {@code MinorVersion}.
 * <p>
 *     The expected version scheme is a simplified variant of the Maven version
 *     scheme without qualifier tags.It follows Forge convention for mod versions that states that
 *     each version string should be composed of only natural numbers separated by dots.
 * </p>
 * @see <a href="https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm#MAVEN8855">
 *     Understanding Maven Version Numbers</a>
 */
@SuppressWarnings("WeakerAccess")
public class ModVersion extends Metadata {

    public enum Group {

        MAJOR, MINOR, PATCH, BUILD;

        /**
         * <p>Read this version group from the given {@code ModVersion}.
         *
         * @param version the {@code ModVersion} to read the version group from.
         * @return the {@code Nth} version group that corresponds to the ordinal of this enumeration constant.
         *         Note that return value will never be a negative value if the version does not have a group
         *         definition that corresponds to the this group the method will return 0.
         */
        public @PositiveOrZero int get(ModVersion version) {
            return version.groups.length > ordinal() ? version.groups[ordinal()] : 0;
        }
    }
    private final @NotEmpty int[] groups;

    protected ModVersion(String value, ForgeMod mod) {

        super(Type.VERSION, value, mod);
        /*
         * The value has been validated in the parent constructor
         * so it's safe to manipulate the value here
         */
        final String[] sVersionGroups = value.split("\\.");
        groups = new int[sVersionGroups.length];

        for (int i = 0; i < sVersionGroups.length; i++) {
            groups[i] = Integer.valueOf(sVersionGroups[i]);
        }
    }

    public int getMajorVersion() {
        return Group.MAJOR.get(this);
    }
    public int getMinorVersion() {
        return Group.MINOR.get(this);
    }
    public int getPatchVersion() {
        return Group.PATCH.get(this);
    }
    public int getBuildNumber() {
        return Group.BUILD.get(this);
    }
}