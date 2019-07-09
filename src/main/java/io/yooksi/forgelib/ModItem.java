package io.yooksi.forgelib;

import io.yooksi.commons.define.MethodsNotNull;
import io.yooksi.commons.util.ArrayUtils;
import io.yooksi.commons.validator.BeanValidator;
import io.yooksi.forgelib.define.ForgeRegIdentifier;
import io.yooksi.forgelib.logger.ForgeLibLogger;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;

@MethodsNotNull
@SuppressWarnings("unused")
public class ModItem extends ItemBase {

    private static Map<ForgeMod, Set<ModItem>> itemRegistry = new java.util.HashMap<>();

    public ModItem(@ForgeRegIdentifier String id, ForgeMod mod) {
        super(id, mod);

        itemRegistry.putIfAbsent(mod, new java.util.HashSet<>());
        itemRegistry.get(mod).add(this);
    }

    /**
     * @return {@code true} if the object corresponding to the provided {@code ItemStack}
     *         has the same runtime class as this {@code ModItem}, and {@code false} otherwise.
     */
    public boolean isItem(net.minecraft.item.ItemStack stack) {
        return getClass() == stack.getItem().getClass();
    }

    /**
     * <p>
     *     Construct and return a new instance of a {@code ModItem} subclass.
     * <p>
     *     All items constructed this way have to have an <i>existing</i> and <i>accessible</i>
     *     constructor whose declared parameters match the ones provided to this method or match
     *     the base {@code ModItem} constructor parameters. Both the order of the parameters as
     *     well as their types have to be an exact match.
     * <p>
     *     Failure to uphold this trust based contract will result in a {@link RuntimeException}.
     *     Read more information about how exactly the parameters are matched and validated in the
     *     documentation of the actual delegate method referenced below.
     * </p>
     * @param itemClass main class of the item to construct.
     * @param name simple item name to be passed as a constructor argument.
     *             Note that mod namespace should not be included.
     * @param mod instance of {@code ForgeMod} constructing the item
     * @param params item class constructor parameters
     * @param <T> a subclass of {@code ModItem} that represents the  item object to construct
     * @return newly constructed and validated item instance
     *
     * @see BeanValidator#constructParent(Class, Class, Object...)
     */
    public static <T extends ModItem> T construct(Class<T> itemClass, String name, ForgeMod mod, Object[] params) {

        ResourceLocation id = new ResourceLocation(mod.getModId(), name);
        ForgeLibLogger.debug("Constructing new item %s(%s) for mod %s", name, id, mod);

        Object[] paramArr = ArrayUtils.addAll(new Object[] { id.toString(), mod }, params);
        return BeanValidator.constructChild(ModItem.class, itemClass, paramArr);
    }
    public static <T extends ModItem> T construct(Class<T> itemClass, String name, ForgeMod mod) {
        return construct(itemClass, name, mod, new Object[]{});
    }
}
