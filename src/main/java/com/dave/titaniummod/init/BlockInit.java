package com.dave.titaniummod.init;

import com.dave.titaniummod.TitaniumMod;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TitaniumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TitaniumMod.MODID)
public class BlockInit {

    public static final Block titanium_ore = null;
    public static final Block titanium_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        //Titanium Ore Block
        event.getRegistry().register(new OreBlock(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 1200.0f)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)
        ).setRegistryName("titanium_ore"));

        //Titanium Ingot Block
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.0f, 1200.0f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)
        ).setRegistryName("titanium_block"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        //Titanium Ore Block - Item
        event.getRegistry().register(new BlockItem(titanium_ore, new Item.Properties()
                .group(TitaniumMod.TitaniumModItemGroup.instance)
                .maxStackSize(64)
        ).setRegistryName("titanium_ore"));

        //Titanium Ingot Block - Item
        event.getRegistry().register(new BlockItem(titanium_block, new Item.Properties()
                .group(TitaniumMod.TitaniumModItemGroup.instance)
                .maxStackSize(64)
        ).setRegistryName("titanium_block"));
    }
}
