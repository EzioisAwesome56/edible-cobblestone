package com.alysoft.ediblecobble.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class LazyBlockItem extends BlockItem {
    public LazyBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        // never be able to place this block
        return false;
    }

    // Hijack getname to make it into an edible version, while retaining
    // the same block name, to reduce on the number of translation file entries
    // required
    @Override
    public Text getName(ItemStack stack) {
        // get base name already
        String base_name = super.getName(stack).getString();
        // get the prefix for block items
        String prefix = Text.translatable("text.edible-cobblestone.prefix").getString();
        // combine the two
        String combined = prefix + " " + base_name;
        // convert back to text
        return Text.of(combined);
    }
}
