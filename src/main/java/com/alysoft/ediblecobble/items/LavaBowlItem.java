package com.alysoft.ediblecobble.items;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LavaBowlItem extends Item {
    public LavaBowlItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        PlayerEntity ent;
        if (user instanceof PlayerEntity){
            ent = (PlayerEntity) user;
            ent.setOnFire(true);
            ent.setOnFireFor(20);
            if (!ent.getAbilities().creativeMode){
                // check to see if the stack contains more then 1 item
                if (itemStack.getCount() > 0) {
                    // create a new item entity of type bowl
                    ItemEntity freebowl = new ItemEntity(world, ent.getX(), ent.getY(), ent.getZ(), new ItemStack(Items.BOWL));
                    // spawn this into the world
                    world.spawnEntity(freebowl);
                    // return the original itemstack
                    return itemStack;
                } else {
                    return new ItemStack(Items.BOWL);
                }
            }
        }
        return itemStack;
    }
}
