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
 *          <dd> {@code PASS} because all annotation method values are valid.
 * </dl>
 * </blockquote>
 */
@Mod(
        modid = "dummyforgemod",        //  All values here are setup correct
        name = "I am a dummy mod",     //  and should PASS Unit tests.
        version = "0.1.0.523"
)
@TestOnly
@SuppressWarnings("NullableProblems")
public class ForgeModWithProperValues extends ForgeMod {

    @Override
    public void preInit(@NotNull FMLPreInitializationEvent event) {}

    @Override
    public void init(@NotNull FMLInitializationEvent event) {}

    @Override
    public void postInit(@NotNull FMLPostInitializationEvent event) {}
}
