package com.alysoft.ediblecobble.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NuclearWasteItem extends Item {
    public NuclearWasteItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        PlayerEntity ent;
        if (user instanceof PlayerEntity){
            ent = (PlayerEntity) user;
            ent.setOnFireFor(6);
        }
        return itemStack;
    }
}
