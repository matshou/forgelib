package io.yooksi.forgelib;

import io.yooksi.forgelib.ForgeMod;
import io.yooksi.forgelib.ModItem;
import io.yooksi.forgelib.forgemod.ForgeModWithProperValues;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;

@SuppressWarnings("WeakerAccess")
public class ModItemTests {

    private static final ForgeMod forgeLib = new ForgeLib();

    // Intended to pass tests
    private class TestItem1 extends ModItem {

        @TestOnly
        public TestItem1(String id, ForgeMod mod) {
            super(id, mod);
        }
    }
    // Intended to fail tests
    private class TestItem2 extends ModItem {

        @TestOnly
        public TestItem2(ForgeMod mod) {
            super("test", mod);
        }
    }

    @Test
    public void constructAndValidateModItemTest() {

        ModItem.construct(TestItem1.class, "testItem1", forgeLib);
    }
}
