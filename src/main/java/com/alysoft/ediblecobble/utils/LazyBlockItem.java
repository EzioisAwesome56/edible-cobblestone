package com.alysoft.ediblecobble.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.Set;

public class LazyBlockItem extends Item {
    private String translate_key;
    public LazyBlockItem(Block block, Settings settings) {
        super(settings);
        this.translate_key = block.getTranslationKey();
    }
    public LazyBlockItem(Settings set){
        super(set);
    }

    // Hijack getname to make it into an edible version, while retaining
    // the same block name, to reduce on the number of translation file entries
    // required
    @Override
    public Text getName(ItemStack stack) {
        // get base name already
        String base_name = Text.translatable(this.translate_key).getString();
        // get the prefix for block items
        String prefix = Text.translatable("text.edible-cobblestone.prefix").getString();
        // combine the two
        String combined = prefix + " " + base_name;
        // convert back to text
        return Text.of(combined);
    }
}
