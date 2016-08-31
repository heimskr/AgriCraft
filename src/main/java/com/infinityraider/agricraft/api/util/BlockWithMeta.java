package com.infinityraider.agricraft.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Simple utility class combining a Block with a meta value.
 */
public class BlockWithMeta {

    private final Block block;
    private final int meta;
    private final boolean ignoreMeta;
    private final boolean useOreDict;
    
    public BlockWithMeta(IBlockState state) {
        this(state.getBlock(), state.getBlock().getMetaFromState(state));
    }

    public BlockWithMeta(Block block) {
        this(block, 0, true, false);
    }

    public BlockWithMeta(Block block, int meta) {
        this(block, meta, false, false);
    }

    public BlockWithMeta(Block block, int meta, boolean fuzzy) {
        this(block, meta, fuzzy, false);
    }

    public BlockWithMeta(Block block, int meta, boolean fuzzy, boolean useOreDict) {
        this.block = block;
        this.meta = meta;
        this.ignoreMeta = fuzzy;
        this.useOreDict = useOreDict;
    }

    public Block getBlock() {
        return block;
    }

    public int getMeta() {
        return meta;
    }

    public boolean ignoreMeta() {
        return ignoreMeta;
    }

    public boolean isUseOreDict() {
        return useOreDict;
    }

    public ItemStack toStack() {
        return new ItemStack(this.block, 1, this.meta);
    }

    @Override
    public String toString() {
        return Block.REGISTRY.getNameForObject(this.block) + ":" + this.meta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof BlockWithMeta) {
            BlockWithMeta block = (BlockWithMeta) obj;
            if ((block.block == this.block) && (this.ignoreMeta || block.ignoreMeta || block.meta == this.meta)) {
                return true;
            }
            if (this.useOreDict || block.useOreDict) {
                int[] ids1 = OreDictionary.getOreIDs(this.toStack());
                int[] ids2 = OreDictionary.getOreIDs(block.toStack());
                for (int id1 : ids1) {
                    for (int id2 : ids2) {
                        if (id1 == id2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
