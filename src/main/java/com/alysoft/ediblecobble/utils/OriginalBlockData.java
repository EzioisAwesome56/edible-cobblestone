package com.alysoft.ediblecobble.utils;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class OriginalBlockData {
    private BlockItem item;
    private Block block;
    private String name;

    public OriginalBlockData(BlockItem i, Block b, String n){
        this.item = i;
        this.block = b;
        this.name = n;
    }

    public Block getBlock() {
        return this.block;
    }

    public BlockItem getItem() {
        return this.item;
    }

    public String getName() {
        return this.name;
    }
}
