package com.alysoft.ediblecobble.items;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class CobblosBowlItem extends Item {
    private int effect;
    public CobblosBowlItem(Settings settings, int effect_no) {
        super(settings);
        this.effect = effect_no;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        PlayerEntity ent;
        if (user instanceof PlayerEntity){
            ent = (PlayerEntity) user;
            /*
            effect id list
            0 -> nuclear
            1 -> milk
            2 -> lava
             */
            switch (this.effect){
                case (0):
                case (1):
                    // make the player not burn anymore, if burning
                    ent.extinguish();
                    break;
                case (2):
                    // ignite the player for eating lava-based foods
                    ent.setOnFire(true);
                    ent.setOnFireFor(10);
                    break;
                default:
                    throw new RuntimeException("Invalid state provided to CobblosBowlItem");
            }

            // return bowl if not in creative mode
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
