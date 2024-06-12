package dev.neuralnexus.taterlib.v1_7_10.forge.world;

import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import net.minecraft.entity.Entity;

/** Forge implementation of {@link Location}. */
public class ForgeLocation implements Location {
    private BlockPos position;
    private float yaw;
    private float pitch;
    private net.minecraft.world.World world;

    /** Creates a new location from an Entity. */
    public ForgeLocation(Entity entity) {
        this(
                new BlockPos(entity.posX, entity.posY, entity.posZ),
                entity.rotationYaw,
                entity.rotationPitch,
                entity.worldObj);
    }

    /** Creates a new location. */
    public ForgeLocation(
            BlockPos position, float yaw, float pitch, net.minecraft.world.World world) {
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
        position = new BlockPos(x, y(), z());
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
        position = new BlockPos(x(), y, z());
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
        position = new BlockPos(x(), y(), z);
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
    public BlockPos blockPosition() {
        return new BlockPos(position.x(), position.y(), position.z());
    }

    /** {@inheritDoc} */
    @Override
    public World world() {
        return new ForgeWorld(world);
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(World world) {
        this.world = ((ForgeWorld) world).world();
    }
}
