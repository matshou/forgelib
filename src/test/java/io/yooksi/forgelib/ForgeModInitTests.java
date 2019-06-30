package io.yooksi.forgelib;

import io.yooksi.forgelib.error.MalformedMetadataException;
import io.yooksi.forgelib.forgemod.ForgeModWithBadId;
import io.yooksi.forgelib.forgemod.ForgeModWithNoId;
import io.yooksi.forgelib.forgemod.ForgeModWithNoName;
import io.yooksi.forgelib.forgemod.ForgeModWithProperValues;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@TestOnly
@SuppressWarnings("WeakerAccess")
public class ForgeModInitTests {

    @Test
    public void forgeModInitializationTest() {

        assertMalformedMetadataException(this::initForgeModWithNoIdShouldFail);
        assertMalformedMetadataException(this::initForgeModWithBadIdShouldFail);

        Assertions.assertDoesNotThrow(this::initForgeModWithNoNameShouldPass);
        Assertions.assertDoesNotThrow(this::initForgeModWithProperValuesShouldPass);
    }

    @Test
    public void forgeModValidateVersionTest() {

        // version = "0.1.0.523"
        ForgeMod mod1 = new ForgeModWithProperValues();

        Assertions.assertEquals(0, mod1.VERSION.getMajorVersion());
        Assertions.assertEquals(1, mod1.VERSION.getMinorVersion());
        Assertions.assertEquals(0, mod1.VERSION.getPatchVersion());
        Assertions.assertEquals(523, mod1.VERSION.getBuildNumber());

        // version = "0.1 -> 0.1.0.0"
        ForgeMod mod2 = new ForgeModWithNoName();

        Assertions.assertEquals(0, mod2.VERSION.getMajorVersion());
        Assertions.assertEquals(1, mod2.VERSION.getMinorVersion());
        Assertions.assertEquals(0, mod2.VERSION.getPatchVersion());
        Assertions.assertEquals(0, mod2.VERSION.getBuildNumber());
    }

    @TestOnly
    private void initForgeModWithNoIdShouldFail() {
        new ForgeModWithNoId();
    }

    @TestOnly
    private void initForgeModWithBadIdShouldFail() {
        new ForgeModWithBadId();
    }

    @TestOnly
    private void initForgeModWithNoNameShouldPass() {
        new ForgeModWithNoName();
    }

    @TestOnly
    private void initForgeModWithProperValuesShouldPass() {
        new ForgeModWithProperValues();
    }

    @TestOnly
    private void assertMalformedMetadataException(Executable method) {
        Assertions.assertThrows(MalformedMetadataException.class, method);
    }
}
