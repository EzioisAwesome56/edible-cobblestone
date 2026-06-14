package com.alysoft.ediblecobble.mixin;

import com.alysoft.ediblecobble.EdibleCobblestone;
import com.alysoft.ediblecobble.items.ModItems;
import com.alysoft.ediblecobble.utils.LazyBlockItem;
import com.alysoft.ediblecobble.utils.OriginalBlockData;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Items.class)
public class ItemsMixin {

    @Inject(method = "register(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;", at = @At(value = "HEAD"), cancellable = true)
    private static void registerHijack(Block block, CallbackInfoReturnable<Item> cir){
        // test if block is cobblestone
        LazyBlockItem funny = null;
        String name = null;
        if (block == Blocks.COBBLESTONE) {
            // make it edible
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(3)
                    .saturationModifier(2.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 1.0f)
                    .build()));
            name = "cobblestone";
            EdibleCobblestone.LOGGER.info("Cobblestone is now edible");
        } else if (block == Blocks.STONE){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(6)
                    .saturationModifier(3.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 2 * 20, 2), 0.5f)
                    .build()));
            name = "stone";
            EdibleCobblestone.LOGGER.info("Stone is now edible");
        } else if (block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(5)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 2), 1.0f)
                    .build()));
            if (block == Blocks.GRANITE){
                name = "granite";
            } else if (block == Blocks.DIORITE){
                name = "diorite";
            } else {
                // assume andesite
                name = "andesite";
            }
            EdibleCobblestone.LOGGER.info("Stupid 1.8 rocks are now edible");
        } else if (block == Blocks.DEEPSLATE || block == Blocks.COBBLED_DEEPSLATE){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(5)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 5), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 20, 2), 0.6f)
                    .build()));
            if (block == Blocks.DEEPSLATE){
                name = "deepslate";
            } else {
                // assume cobbled deepslate
                name = "cobbled_deepslate";
            }
            EdibleCobblestone.LOGGER.info("Deepslate is now edible");
        } else if (block == Blocks.NETHERRACK){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(4)
                    .saturationModifier(2.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 1), 0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 30 * 20, 1), 1.0f)
                    .build()));
            name = "netherrack";
            EdibleCobblestone.LOGGER.info("Netherrack is now edible");
        } else if (block == Blocks.TUFF){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(3)
                    .saturationModifier(0.5f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 0.8f)
                    .build()));
            name = "tuff";
            EdibleCobblestone.LOGGER.info("Tuff is now edible");
        } else if (block == Blocks.MOSSY_COBBLESTONE){
            funny = new LazyBlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(5)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 10, 1), 0.5f)
                    .build()));
            name = "mossy_cobblestone";
            EdibleCobblestone.LOGGER.info("Mossy Cobblestone is now edible");
        }
        // check if we need to do anything
        if (funny != null){
            // register the item
            //Item what = register(block, funny);
            ModItems.addMcBlockToList(new OriginalBlockData(funny, block, name));
            /*cir.setReturnValue(what);
            cir.cancel();*/
        }
    }
}
