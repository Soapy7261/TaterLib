package dev.neuralnexus.taterlib.b1_7_3.bukkit.event.block;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.block.BukkitBlock;
import dev.neuralnexus.taterlib.block.Block;
import dev.neuralnexus.taterlib.event.block.BlockEvent;

/** Bukkit implementation of {@link BlockEvent}. */
public class BukkitBlockEvent implements BlockEvent {
    private final org.bukkit.event.block.BlockEvent event;

    public BukkitBlockEvent(org.bukkit.event.block.BlockEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Block block() {
        return new BukkitBlock(event.getBlock());
    }
}
