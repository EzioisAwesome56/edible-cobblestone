package com.alysoft.ediblecobble.mixin;

import com.alysoft.ediblecobble.EdibleCobblestone;
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

import static net.minecraft.item.Items.register;

@Mixin(Items.class)
public class ItemsMixin {

    @Inject(method = "register(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;", at = @At(value = "HEAD"), cancellable = true)
    private static void registerHijack(Block block, CallbackInfoReturnable<Item> cir){
        // test if block is cobblestone
        BlockItem funny = null;
        if (block == Blocks.COBBLESTONE) {
            // make it edible
            funny = new BlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(3)
                    .saturationModifier(2.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 1.0f)
                    .build()));
            EdibleCobblestone.LOGGER.info("Cobblestone is now edible");
        } else if (block == Blocks.STONE){
            funny = new BlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(6)
                    .saturationModifier(3.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 2), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 2 * 20, 2), 0.5f)
                    .build()));
            EdibleCobblestone.LOGGER.info("Stone is now edible");
        } else if (block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE){
            funny = new BlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(5)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 2), 1.0f)
                    .build()));
            EdibleCobblestone.LOGGER.info("Stupid 1.8 rocks are now edible");
        } else if (block == Blocks.DEEPSLATE || block == Blocks.COBBLED_DEEPSLATE){
            funny = new BlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(5)
                    .saturationModifier(1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30 * 20, 5), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 20, 2), 0.6f)
                    .build()));
            EdibleCobblestone.LOGGER.info("Deepslate is now edible");
        } else if (block == Blocks.NETHERRACK){
            funny = new BlockItem(block, new FabricItemSettings().food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(4)
                    .saturationModifier(2.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 20, 1), 0.8f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 30 * 20, 1), 1.0f)
                    .build()));
            EdibleCobblestone.LOGGER.info("Netherrack is now edible");
        }
        // check if we need to do anything
        if (funny != null){
            // register the item
            Item what = register(block, funny);
            cir.setReturnValue(what);
            cir.cancel();
        }
    }
}
