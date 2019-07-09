package io.yooksi.forgelib;

import io.yooksi.forgelib.logger.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class RegistryHandler {

    private final ModLogger logger;
    private final List<Item> items;

    public RegistryHandler(ModLogger logger, List<Item> items) {
        this.logger = logger; this.items = items;
    }

    public void registerItems(RegistryEvent.Register<Item> event) {

        logger.info("Registering mod items...");;
        final IForgeRegistry<Item> registry = event.getRegistry();
        items.forEach(registry::register);
    }

    public void registerModels(ModelRegistryEvent event)
    {
        logger.info("Registering asset models...");
        items.forEach(this::registerModel);
    }

    @SuppressWarnings("unused")
    private void registerModel(Block block)
    {
        registerModel(Item.getItemFromBlock(block));
    }

    private void registerModel(Item item)
    {
        net.minecraft.util.ResourceLocation location = java.util.Objects.requireNonNull(item.getRegistryName());
        ModelResourceLocation modelLocation = new ModelResourceLocation(location, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, modelLocation);
    }
}
