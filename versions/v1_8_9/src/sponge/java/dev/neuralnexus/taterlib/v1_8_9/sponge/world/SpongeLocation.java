/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.world;

import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

/** Sponge implementation of {@link Location}. */
public class SpongeLocation implements Location {
    private final org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location;

    /** Creates a new location. */
    public SpongeLocation(
            org.spongepowered.api.world.Location<org.spongepowered.api.world.World> location) {
        this.location = location;
    }

    @Override
    public void setX(double x) {
        location.add(x - location.getX(), 0, 0);
    }

    @Override
    public void setY(double y) {
        location.add(0, y - location.getY(), 0);
    }

    @Override
    public void setZ(double z) {
        location.add(0, 0, z - location.getZ());
    }

    @Override
    public float yaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    @Override
    public void setYaw(float yaw) {
        // TODO: Find a way to set the yaw
    }

    @Override
    public float pitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    @Override
    public void setPitch(float pitch) {
        // TODO: Find a way to set the pitch
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public World world() {
        return new SpongeWorld(location.getExtent());
    }

    @Override
    public void setWorld(World world) {
        location.setExtent(((SpongeWorld) world).unwrap());
    }
}
