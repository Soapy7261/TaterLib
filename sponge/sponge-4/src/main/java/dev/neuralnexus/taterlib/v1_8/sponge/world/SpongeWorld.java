/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_8.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_8.sponge.player.SpongePlayer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link World}. */
public class SpongeWorld implements World {
    private final org.spongepowered.api.world.World level;

    public SpongeWorld(org.spongepowered.api.world.World level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public org.spongepowered.api.world.World world() {
        return level;
    }

    /** {@inheritDoc} */
    @Override
    public List<Player> players() {
        return level.getEntities().stream()
                .filter(e -> e instanceof org.spongepowered.api.entity.living.player.Player)
                .map(org.spongepowered.api.entity.living.player.Player.class::cast)
                .map(SpongePlayer::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public ResourceKey dimension() {
        return ResourceKey.of(level.getName());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        return level.getEntities().stream()
                .map(SpongeEntity::new)
                .filter(e -> e.location().distance(entity.location()) <= radius)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level.getEntities().stream()
                .map(SpongeEntity::new)
                .filter(e -> e.location().x() >= pos1.x() && e.location().x() <= pos2.x())
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
