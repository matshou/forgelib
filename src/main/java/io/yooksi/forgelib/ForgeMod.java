package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.forgelib.error.IllegalModStateException;
import io.yooksi.forgelib.logger.ForgeLibLogger;
import io.yooksi.forgelib.meta.Metadata;
import io.yooksi.forgelib.meta.ModVersion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.Nullable;

/**
 * <p>
 *     Every {@code ForgeMod} implementation should represent a main Forge mod class and is thus expected
 *     to be annotated as a {@link Mod}. Unlike before your mod no longer needs to contain static metadata
 *     information like {@code MOD_ID}, {@code VERSION}, and {@code NAME} used by Forge to register and handle
 *     each mod. These fields are now publicly declared here and wrapped in a container class that both stores
 *     and validates the data which is directly read from {@code Mod} annotation method fields.
 * <p>
 *     The class constructor will automatically detect mod metadata by reading {@code @Mod} annotation method
 *     return values and store them in private fields that can be read by using the provided public getters.
 *     In case the information is either missing or malformed a {@link IllegalModStateException} is thrown.
 * <p>
 *     Even though the class requires initialization method implementation the methods themselves still need
 *     to be manually annotated with {@link Mod.EventHandler} for them to be called by Forge. This is true for
 *     all class and method implementation in this library that are intended to be called by Forge during runtime.
 * </p>
 * @see ForgeLib
 */
@MethodsNotNull
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ForgeMod {

    private final @Nullable CreativeTabs tab;

    /**
     * <p>
     *     The unique mod identifier for this mod required by {@link Mod#modid()}.
     * <p><b>
     *     Required to be lower-cased in the english locale for compatibility.
     *     Will be truncated to 64 characters long.
     * </b>
     * This will be used to identify your mod for third parties (other mods), it will be used to identify
     * your mod for registries such as block and item registries.
     * <p>
     * By default, you will have a resource domain that matches the modid.
     * All these uses require that constraints are imposed on the format of the modid.
     */
    public final Metadata MOD_ID;
    /**
     * A user friendly name for this mod.
     */
    public final Metadata NAME;
    /**
     *
     * A version string for this mod.
     *
     * The version string here should be just numbers separated by dots,
     * to make specifying {@link Mod#dependencies()} simple for other mods.
     *
     * @see <a href="https://cwiki.apache.org/confluence/display/MAVENOLD/Versioning">
     *     "Versioning" on Maven Wiki</a>
     */
    public final ModVersion VERSION;

    /**
     * <p>Create a new {@code ForgeMod} that uses an existing {@code CreativeTabs}.
     * <p>This is a top-class constructor that handles class field initialization.
     *
     * @param tab {@code CreativeTabs} existing {@code CreativeTabs} to use.All the ones used by vanilla
     *            are statically defined and publicly accessible in {@code CreativeTabs} class.
     *            Each created tab is stored in {@link CreativeTabs#CREATIVE_TAB_ARRAY}.
     *
     * @throws IllegalModStateException when the implementation class is not annotated as a {@link Mod}.
     */
    public ForgeMod(@Nullable CreativeTabs tab) {

        this.tab = tab;

        Mod mod = getClass().getAnnotation(Mod.class);
        /*
         * Check if class annotation is present
         */
        if (mod == null) {
            throw IllegalModStateException.Cause.NOT_ANNOTATED.create(this);
        }
        /* Construct and validate metadata objects
         */
        MOD_ID = Metadata.Type.MOD_ID.create(mod.modid(), this);
        NAME = Metadata.Type.NAME.create(mod.name(), this);
        VERSION = Metadata.Type.VERSION.create(mod.version(), this);

        String log = "Created and registered new Forge mod: \"%s\" (%s-v%s)";
        ForgeLibLogger.info(log, getName(), getModId(), getVersion());
    }

    /**
     * Create a new {@code ForgeMod} with a custom {@code CreativeTabs}.
     *
     * @param tabName {@code CreativeTabs} label used to display a friendly name in UI.
     * @param tabIcon this item's icon will be used for the creative tab.
     *
     * @see CreativeTabs#getTabLabel()
     * @see CreativeTabs#getTranslationKey()
     * @see CreativeTabs#getIcon()
     */
    public ForgeMod(String tabName, net.minecraft.item.Item tabIcon) {
        /*
         *  Create a new creative tab to be displayed in-game for our mod.
         *  By overriding {@code getTabIconItem} we are able to return our own
         *  item stack which the game will use to show a tab picture.
         */
        this(new net.minecraft.creativetab.CreativeTabs(tabName) {

            @Override
            public net.minecraft.item.ItemStack createIcon() {
                return new net.minecraft.item.ItemStack(tabIcon);
            }
        });
    }

    /**
     * Create a new {@code ForgeMod} with no {@code CreativeTabs}.
     * <p>In the absence of another constructor this constructor is invoked by default.
     *
     * @see ForgeMod#ForgeMod(CreativeTabs)
     */
    public ForgeMod() { this(null); }

    public final @Nullable CreativeTabs getCreativeTab() {
        return tab;
    }

    /*
     * You can override these if you want to return different
     * mod id, name or version information:
     */

    public String getModId() {
        return MOD_ID.getValue();
    }
    public String getName() {
        return NAME.getValue();
    }
    public String getVersion() {
        return VERSION.getValue();
    }

    /*
     * The following initialization methods HAVE to be
     * implemented by every ForgeMod class:
     */

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    abstract public void preInit(FMLPreInitializationEvent event);

    /**
     * This is the second initialization event. Register custom recipes.
     */
    abstract public void init(FMLInitializationEvent event);

    /**
     * This is the final initialization event. Register actions from other mods here.
     */
    abstract public void postInit(FMLPostInitializationEvent event);
}
