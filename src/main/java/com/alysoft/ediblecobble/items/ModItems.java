package com.alysoft.ediblecobble.items;

import com.alysoft.ediblecobble.EdibleCobblestone;
import com.alysoft.ediblecobble.utils.OriginalBlockData;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final Item WASTE = register(new Item(new FabricItemSettings().maxCount(64)), "waste");
    public static final Item NUCLEAR_WASTE = register(new NuclearWasteItem(new FabricItemSettings().maxCount(64)
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 4 * 20, 2), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 5 * 20, 10), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1), 1.0f).build())), "nuclear_waste");
    public static final Item GREEN_WASTE = register(new Item(new FabricItemSettings().maxCount(64)), "green_waste");
    public static final Item COBBLESTONE_OS = register(new Item(new FabricItemSettings().maxCount(64)), "cobblestone_os");
    public static final Item OBSIDIAN_OS = register(new Item(new FabricItemSettings().maxCount(64)), "obsidian_os");
    public static final Item COBBLESTONE_BOWL_NUCLEAR = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(7)
            .saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 180 * 20, 10), 0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 3 * 20, 1), 0.5f).build()), 0), "cobblestone_bowl");

    public static final Item COBBLESTONE_BOWL_MILK = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(11)
            .saturationModifier(1.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40 * 20, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20, 1), 1.0f).build()), 1), "cobblestone_bowl_milk");

    public static final Item COBBLESTONE_BOWL_LAVA = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(3.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 180 * 20, 3), 0.3f).build()), 2), "cobblestone_bowl_lava");

    public static final Item OBSIDIAN_BOWL = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(4)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30 * 20, 3), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 3), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 5 * 20, 1), 0.8f)
            .build()), 0), "obsidian_bowl");
    public static final Item OBSIDIAN_BOWL_MILK = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(8)
            .saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30 * 20, 4), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 25 * 20, 1), 0.4f)
            .build()), 1), "obsidian_bowl_milk");
    public static final Item OBSIDIAN_BOWL_LAVA = register(new CobblosBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(8)
            .saturationModifier(2.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30 * 20, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 3), 1.0f)
            .build()), 2), "obsidian_bowl_lava");

    public static final Item LAVA_BOWL = register(new LavaBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .hunger(4).alwaysEdible().build())), "lava_bowl");
    public static final Item SODIUM_BOWL = register(new SodiumBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .alwaysEdible()
            .saturationModifier(0.7f)
            .hunger(2).build()), 1), "sodium_bowl");
    public static final Item SUPER_SODIUM_BOWL = register(new SodiumBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .alwaysEdible()
            .saturationModifier(0.7f)
            .hunger(2).build()), 4), "super_sodium_bowl");
    public static final Item DUPER_SODIUM_BOWL = register(new SodiumBowlItem(new FabricItemSettings().maxCount(64).food(new FoodComponent.Builder()
            .alwaysEdible()
            .saturationModifier(0.7f)
            .hunger(2).build()), 9), "duper_sodium_bowl");
    public static Item register(Item item, String id){
        // stolen from the fabric API documentation
        // adapted for yarn mappings (the mojmaps dont seem to work right for the old versions...)
        Identifier itemID = new Identifier(EdibleCobblestone.MOD_ID, id);
        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        // Return the registered item!
        return registeredItem;
    }

    private static final ArrayList<OriginalBlockData> mcblocks = new ArrayList<>();
    public static void addMcBlockToList(OriginalBlockData b){
        // put it in the list
        mcblocks.add(b);
    }
    public static void convertMcToFoodItems(){
        // create a new item for each vanilla rock added to the list
        for (OriginalBlockData og : mcblocks){
            // get the string name of the block
            String name = og.getName();
            // feed it the block name with a prefix
            Item theitem = register(og.getItem(), "edible_" + name);
            // put them in the creative tab
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
                content.add(theitem);
            });
        }
    }

    public static void init_items() {
        // list for being insanely lazy later
        List<Item> temp = new ArrayList<>();
        temp.add(WASTE);
        temp.add(GREEN_WASTE);
        temp.add(NUCLEAR_WASTE);
        temp.add(COBBLESTONE_OS);
        temp.add(COBBLESTONE_BOWL_NUCLEAR);
        temp.add(COBBLESTONE_BOWL_MILK);
        temp.add(COBBLESTONE_BOWL_LAVA);
        temp.add(OBSIDIAN_OS);
        temp.add(OBSIDIAN_BOWL);
        temp.add(OBSIDIAN_BOWL_MILK);
        temp.add(OBSIDIAN_BOWL_LAVA);
        temp.add(LAVA_BOWL);
        temp.add(SODIUM_BOWL);
        temp.add(SUPER_SODIUM_BOWL);
        temp.add(DUPER_SODIUM_BOWL);
        // add items to ingredients creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            for (Item i : temp){
                content.add(i);
            }
        });
    }
}
