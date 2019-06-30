package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * {@inheritDoc}
 */
@MethodsNotNull
@Mod(modid = "forgelib", name = "ForgeLib", version = "0.1")
public final class ForgeLib extends ForgeMod {

    /**
     * Use this to initialize {@link Mod#dependencies()} field for your mod.
     * It will tell Forge that your mod depends on {@code ForgeLib} in order to run.
     */
    @SuppressWarnings("unused")
    public static final String DEPENDENT = "required-after:forgelib@[0.1,);";

    @Override
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Override
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
