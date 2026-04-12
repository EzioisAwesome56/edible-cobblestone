package com.alysoft.ediblecobble.items;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

public class SodiumBowlItem extends Item {
    private double multiply;
    public SodiumBowlItem(Settings settings, double multiplier) {
        super(settings);
        this.multiply = multiplier;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        PlayerEntity ent;
        if (user instanceof PlayerEntity){
            ent = (PlayerEntity) user;
            // create a explosion
            world.createExplosion(ent, ent.getX(), ent.getY(), ent.getZ(), 2.0f * (float) multiply, World.ExplosionSourceType.TNT);
            // do some damage
            //ent.damage(new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypes.EXPLOSION)), 7.0f);
            // send the player up into the air
            ent.addVelocity(new Vec3d(0D, 3D * multiply, 0D));
            // setup a timer to hit the player with posion in a couple of seconds
            Timer timer = new Timer();
            TimerTask delayedPosion = new TimerTask() {
                @Override
                public void run() {
                    ent.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 7 * 20, 50));
                }
            };
            TimerTask delayedDeath = new TimerTask() {
                @Override
                public void run() {
                    if (!ent.isDead() || ent.deathTime < 7){
                        // murder via any means
                        ent.damage(new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypes.MAGIC)), 99.0f);
                    }
                }
            };
            // schedule the task
            timer.schedule(delayedPosion,  1000 / 2);
            timer.schedule(delayedDeath, (long) ((3 * 1000) * multiply));
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
