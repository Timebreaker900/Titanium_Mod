package com.dave.titaniummod;

import com.dave.titaniummod.init.BlockInit;
import com.dave.titaniummod.world.gen.TitaniumModOreGen;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("titaniummod")
public class TitaniumMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "titaniummod";
    public static TitaniumMod instance;

    public TitaniumMod() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(this::loadCompleteEvent);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public void loadCompleteEvent(FMLLoadCompleteEvent event) {
        TitaniumModOreGen.generateOre();
    }


    public static class TitaniumModItemGroup extends ItemGroup {

        public static final TitaniumModItemGroup instance = new TitaniumModItemGroup(ItemGroup.GROUPS.length, "titaniummodtab");
        public TitaniumModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.titanium_block);
        }
    }
}
