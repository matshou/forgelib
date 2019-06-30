package io.yooksi.forgelib.forgemod;

import io.yooksi.forgelib.ForgeMod;
import io.yooksi.forgelib.error.MalformedMetadataException;
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
 *          <dd> {@code FAIL} by throwing a {@link MalformedMetadataException}.
 *     <dt><b>Reason:</b>
 *          <dd> {@link Mod#modid()} annotation method value is malformed because it
 *               contains whitespaces and special characters.
 * </dl>
 * </blockquote>
 */
@Mod(
        modid = "$ab c",                // This ForgeMod should FAIL Unit testing
        name = "I am a dummy mod",     // because ModId value is malformed
        version = "0.1.0.523"
)
@TestOnly
@SuppressWarnings("NullableProblems")
public class ForgeModWithBadId extends ForgeMod {

    @Override
    public void preInit(@NotNull FMLPreInitializationEvent event) {}

    @Override
    public void init(@NotNull FMLInitializationEvent event) {}

    @Override
    public void postInit(@NotNull FMLPostInitializationEvent event) {}
}
