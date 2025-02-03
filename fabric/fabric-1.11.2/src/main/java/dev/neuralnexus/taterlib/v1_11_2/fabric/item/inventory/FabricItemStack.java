/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.fabric.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;

import net.minecraft.item.Items;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Fabric implementation of {@link ItemStack}. */
public class FabricItemStack implements ItemStack, Wrapped<net.minecraft.item.ItemStack> {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Fabric item stack.
     */
    public FabricItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.item.ItemStack(Items.AIR) : itemStack;
    }

    @Override
    public net.minecraft.item.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(this.itemStack.getItem().toString());
    }

    @Override
    public int count() {
        return this.itemStack.getCount();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setCount(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new FabricItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasCustomName();
    }

    @Override
    public Optional<String> displayName() {
        return Optional.of(this.itemStack.getCustomName());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.setCustomName(name);
    }

    @Override
    public boolean hasLore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<String> lore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasEnchants() {
        Objects.requireNonNull(this.itemStack.getEnchantments());
        return !itemStack.getEnchantments().isEmpty();
    }

    @Override
    public boolean unbreakable() {
        Objects.requireNonNull(this.itemStack.getNbt());
        return this.itemStack.hasNbt() && this.itemStack.getNbt().getBoolean("Unbreakable");
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.itemStack.getOrCreateNbtCompound("Unbreakable").putBoolean("Unbreakable", unbreakable);
    }
}
