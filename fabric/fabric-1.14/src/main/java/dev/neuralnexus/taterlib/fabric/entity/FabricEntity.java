package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

import java.util.UUID;

/** Fabric implementation of {@link Entity}. */
public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Fabric entity.
     *
     * @return The Fabric entity.
     */
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUuid();
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
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TranslatableComponent(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return entity.getPos().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return entity.getPos().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return entity.getPos().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return entity.getYaw(0F);
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return entity.getPitch(0F);
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.getEntityWorld().getDimension().getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.getEntityWorld()
                .getBiome(entity.getBlockPos())
                .getTextComponent()
                .getFormattedText();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
            MinecraftServer server = FabricTaterLibPlugin.server;
            if (server == null) return;
            DimensionType dimension =
                    DimensionType.byId(new Identifier(location.world().split(":")[1]));
            ServerWorld serverLevel = server.getWorld(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                player.teleport(
                        serverLevel,
                        location.x(),
                        location.y(),
                        location.z(),
                        player.yaw,
                        player.pitch);
                return;
            } else {
                entity.changeDimension(dimension);
            }
        }
        entity.requestTeleport(location.x(), location.y(), location.z());
    }
}
