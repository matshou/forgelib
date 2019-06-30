package io.yooksi.forgelib.forgemod;

import io.yooksi.forgelib.ForgeMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

/**
 * Used for unit testing mod initialization.
 * <blockquote>
 * <dl>
 *     <dt><b>Expected Result:</b>
 *          <dd> {@code PASS} because {@link Mod#name()} value is allowed to be empty.
 * </dl>
 * </blockquote>
 */
@Mod(
        modid = "dummyforgemod",        // This ForgeMod does not have a name but should
        version = "0.1"                // PASS Unit testing because that value is not required
)
@TestOnly
@SuppressWarnings("NullableProblems")
public class ForgeModWithNoName extends ForgeMod {

    @Override
    public void preInit(@NotNull FMLPreInitializationEvent event) {}

    @Override
    public void init(@NotNull FMLInitializationEvent event) {}

    @Override
    public void postInit(@NotNull FMLPostInitializationEvent event) {}
}
