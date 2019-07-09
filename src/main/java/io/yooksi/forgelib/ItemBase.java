package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.forgelib.define.ForgeRegIdentifier;
import io.yooksi.forgelib.logger.ForgeLibLogger;
import net.minecraft.item.Item;

@MethodsNotNull
@SuppressWarnings({"unused", "WeakerAccess"})
public class ItemBase extends Item {

    public ItemBase(@ForgeRegIdentifier String id, ForgeMod mod) {

        setRegistryName(id);
        setTranslationKey(id);

        // It's actually okay to pass null here
        // noinspection ConstantConditions
        setCreativeTab(mod.getCreativeTab());

        ForgeLibLogger.debug("Created new item %s for mod %s", id, mod);
    }
}
