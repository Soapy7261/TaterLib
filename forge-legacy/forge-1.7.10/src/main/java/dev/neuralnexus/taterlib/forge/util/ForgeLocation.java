package dev.neuralnexus.taterlib.forge.util;

import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;

import net.minecraft.entity.Entity;

/** Fabric implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private Position position;
    private float yaw;
    private float pitch;
    private String world;

    /** Creates a new location from an Entity. */
    public ForgeLocation(Entity entity) {
        this(
                new Position(entity.posX, entity.posY, entity.posZ),
                entity.rotationYaw,
                entity.rotationPitch,
                new ForgeEntity(entity).dimension());
    }

    /** Creates a new location. */
    public ForgeLocation(Position position, float yaw, float pitch, String world) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return position.x();
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        position = new Position(x, y(), z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return Math.floor(x());
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return position.y();
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        position = new Position(x(), y, z());
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return Math.floor(y());
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return position.z();
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        position = new Position(x(), y(), z);
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return Math.floor(z());
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return yaw;
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return pitch;
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /** {@inheritDoc} */
    @Override
    public Position blockPosition() {
        return new Position(blockX(), blockY(), blockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String world() {
        return world;
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }
}
