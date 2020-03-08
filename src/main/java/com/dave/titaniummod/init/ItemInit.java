package com.dave.titaniummod.init;

import com.dave.titaniummod.TitaniumMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TitaniumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TitaniumMod.MODID)
public class ItemInit {

    //Ingot
    public static final Item titanium_ingot = null;

    //Tools
    public static final Item titanium_sword = null;
    public static final Item titanium_pickaxe = null;
    public static final Item titanium_shovel = null;
    public static final Item titanium_axe = null;
    public static final Item titanium_hoe = null;

    //Armor
    public static final Item titanium_helmet = null;
    public static final Item titanium_chestplate = null;
    public static final Item titanium_leggings = null;
    public static final Item titanium_boots = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        //Ingot
        event.getRegistry().register(new Item(new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_ingot"));

        //Tools
        event.getRegistry().register(new SwordItem(ModItemTier.TITANIUM, 4, -2.4f,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.TITANIUM, 2, -2.8F,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_pickaxe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.TITANIUM, 2.5F, -3.0F,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_shovel"));
        event.getRegistry().register(new AxeItem(ModItemTier.TITANIUM, 6, -3.0F,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_axe"));
        event.getRegistry().register(new HoeItem(ModItemTier.TITANIUM, 0.0F,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_hoe"));

        //Armor
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.HEAD,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_helmet"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.CHEST,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_chestplate"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.LEGS,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_leggings"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.FEET,
                new Item.Properties().group(TitaniumMod.TitaniumModItemGroup.instance)).setRegistryName("titanium_boots"));
    }

    public enum ModItemTier implements IItemTier {

        TITANIUM(4, 2500, 8.0F, 3.0F, 14, () -> {
            return Ingredient.fromItems(ItemInit.titanium_ingot);
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }

    public enum ModArmorMaterial implements IArmorMaterial {
        TEST(TitaniumMod.MODID + ":titanium", 5, new int[]{4, 7, 9, 4}, 420, SoundEvents.field_226124_Y_, 2.5F, () -> {
            return Ingredient.fromItems(ItemInit.titanium_ingot);
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[]{16, 16, 16, 16};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;

        private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
        }

        @Override
        public int getDurability(EquipmentSlotType equipmentSlotType) {
            return MAX_DAMAGE_ARRAY[equipmentSlotType.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType equipmentSlotType) {
            return this.damageReductionAmountArray[equipmentSlotType.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
}
