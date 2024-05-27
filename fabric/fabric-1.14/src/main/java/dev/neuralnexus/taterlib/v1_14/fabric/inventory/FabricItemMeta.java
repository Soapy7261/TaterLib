package dev.neuralnexus.taterlib.v1_14.fabric.inventory;

import net.minecraft.item.ItemStack;

/** Abstracts a Fabric item meta to an AbstractItemMeta. */
public class FabricItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Fabric item stack.
     */
    public FabricItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
