/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16.vanilla.accessors.world.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/** Accessor for {@link LivingEntity}. */
@ReqMCVersion(min = MinecraftVersion.V1_16, max = MinecraftVersion.V1_16_5)
@Mixin(LivingEntity.class)
@SuppressWarnings("UnusedMixin")
public interface LivingEntityAccessor {
    @Invoker("getExperienceReward")
    int invoker$getExperienceReward(final Player player);
}
