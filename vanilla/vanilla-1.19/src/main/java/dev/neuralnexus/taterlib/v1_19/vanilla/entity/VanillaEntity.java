package dev.neuralnexus.taterlib.v1_19.vanilla.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_19.vanilla.util.VanillaLocation;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
import java.util.UUID;

/** Vanilla implementation of {@link Entity}. */
public class VanillaEntity implements Entity {
    private final net.minecraft.world.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public VanillaEntity(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.world.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
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
        entity.setCustomName(Component.nullToEmpty(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new VanillaLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return entity.getX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return entity.getY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return entity.getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return entity.getYRot();
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return entity.getXRot();
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.getLevel().dimension().location().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        Optional<ResourceKey<Biome>> holder =
                entity.getLevel().getBiome(entity.blockPosition()).unwrap().left();
        return holder.map(biomeResourceKey -> biomeResourceKey.registry().toString()).orElse(null);
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
            MinecraftServer server = VanillaServer.server();
            if (server == null) return;
            ResourceKey<Level> dimension =
                    ResourceKey.create(
                            Registry.DIMENSION_REGISTRY, new ResourceLocation(location.world()));
            ServerLevel serverLevel = server.getLevel(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel,
                        location.x(),
                        location.y(),
                        location.z(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel);
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
