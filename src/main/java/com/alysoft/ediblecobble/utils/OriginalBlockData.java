package com.alysoft.ediblecobble.utils;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class OriginalBlockData {
    private LazyBlockItem item;
    private Block block;
    private String name;

    public OriginalBlockData(LazyBlockItem i, Block b, String n){
        this.item = i;
        this.block = b;
        this.name = n;
    }

    public Block getBlock() {
        return this.block;
    }

    public LazyBlockItem getItem() {
        return this.item;
    }

    public String getName() {
        return this.name;
    }
}
