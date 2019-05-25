package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.validator.BeanValidator;
import io.yooksi.forgelib.define.ForgeRegIdentifier;
import io.yooksi.forgelib.logger.ForgeLibLogger;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

@MethodsNotNull
public class ItemBase extends Item {

    public ItemBase(@ForgeRegIdentifier String id, ForgeMod mod) {
        
        setRegistryName(id);
        setTranslationKey(id);
        setCreativeTab(mod.getCreativeTab());
        ForgeLibLogger.debug("Created new item %s for mod %s", id, mod);
    }

    /**
     * Helper method to construct and validate new simple custom items.
     *
     * @param itemClass main class of the item to construct
     * @param name simple item name <i>(excluding namespace)</i>
     * @param mod instance of the mod constructing the item
     * @return newly constructed and validated item instance
     */
    public static <T extends ItemBase> T construct(Class<T> itemClass, String name, ForgeMod mod) {

        ResourceLocation id = new ResourceLocation(mod.getModId(), name);
        ForgeLibLogger.debug("Constructing new item %s(%s) for mod %s", name, id, mod);

        return BeanValidator.constructChild(ItemBase.class, itemClass, id.toString(), mod);
    }
}
