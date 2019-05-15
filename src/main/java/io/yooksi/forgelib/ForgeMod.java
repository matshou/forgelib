package io.yooksi.forgelib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;

public abstract class ForgeMod {

    private final CreativeTabs tab;

    public ForgeMod(String tabName, net.minecraft.item.Item tabIcon) {
        /*
         *  Create a new creative tab to be displayed in-game for our mod.
         *  By overriding {@code getTabIconItem} we are able to return our own
         *  item stack which the game will use to show a tab picture.
         */
        tab = (new net.minecraft.creativetab.CreativeTabs(tabName) {

            @Override @NotNull
            public net.minecraft.item.ItemStack createIcon() {
                return new net.minecraft.item.ItemStack(tabIcon);
            }
        });
    }

    public CreativeTabs getCreativeTab() {
        return tab;
    }

    abstract public String getModId();

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    abstract public void preInit(FMLPreInitializationEvent event);

    /**
     * This is the second initialization event. Register custom recipes
     */
    abstract public void init(FMLInitializationEvent event);

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    abstract public void postInit(FMLPostInitializationEvent event);
}
