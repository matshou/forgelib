package io.yooksi.forgelib.forgemod;

import io.yooksi.forgelib.ForgeMod;
import io.yooksi.forgelib.error.IllegalModStateException;
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
 *          <dd> {@code FAIL} by throwing a {@link IllegalModStateException}.
 *     <dt><b>Reason:</b>
 *          <dd> Mod class is not annotated with Forge {@link Mod} annotation.
 * </dl>
 * </blockquote>
 */
@TestOnly
@SuppressWarnings("NullableProblems")
public class ForgeModWithNoAnnotation extends ForgeMod {

    @Override
    public void preInit(@NotNull FMLPreInitializationEvent event) {}

    @Override
    public void init(@NotNull FMLInitializationEvent event) {}

    @Override
    public void postInit(@NotNull FMLPostInitializationEvent event) {}
}
