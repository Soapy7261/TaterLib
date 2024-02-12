package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.UUID;

/** Forge implementation of {@link Entity}. */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     *
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TextComponentString(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new ForgeLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return entity.getPosition().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return entity.getPosition().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return entity.getPosition().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return entity.getPitchYaw().x;
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return entity.getPitchYaw().y;
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.world.dimension.getType().getRegistryName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        ResourceLocation biomeRegistry =
                entity.world.getBiome(entity.getPosition()).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) return;
            DimensionType dimension =
                    DimensionType.byName(new ResourceLocation(location.world().split(":")[1]));
            if (dimension == null) return;
            WorldServer serverLevel = server.getWorld(dimension);
            if (entity instanceof EntityPlayerMP) {
                ((EntityPlayerMP) entity)
                        .teleport(
                                serverLevel,
                                location.x(),
                                location.y(),
                                location.z(),
                                entity.rotationYaw,
                                entity.rotationPitch);
                return;
            } else {
                entity.changeDimension(dimension, new Teleporter(serverLevel));
            }
        }
        ((EntityLiving) entity).attemptTeleport(location.x(), location.y(), location.z());
    }
}
