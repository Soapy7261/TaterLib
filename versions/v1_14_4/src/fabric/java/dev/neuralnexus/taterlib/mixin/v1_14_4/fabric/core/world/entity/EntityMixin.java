/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.EntityBridge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2)
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {
    @Shadow
    public abstract void shadow$sendMessage(Component message);

    @Shadow
    public abstract void shadow$remove();

    @Shadow
    public abstract Level shadow$getCommandSenderWorld();

    @Shadow
    public abstract BlockPos shadow$getCommandSenderBlockPosition();

    @Shadow
    public abstract Entity shadow$changeDimension(DimensionType dimensionType);

    @Shadow
    public abstract void shadow$setCustomName(@Nullable Component name);

    @Override
    public void bridge$sendMessage(String message) {
        this.shadow$sendMessage(new TextComponent(message));
    }

    @Override
    public void bridge$remove() {
        this.shadow$remove();
    }

    @Override
    @SuppressWarnings("resource")
    public ResourceLocation bridge$biome() {
        return Registry.BIOME.getKey(
                this.shadow$getCommandSenderWorld()
                        .getBiome(this.shadow$getCommandSenderBlockPosition()));
    }

    @Override
    public void bridge$changeDimension(ServerLevel level) {
        this.shadow$changeDimension(level.getDimension().getType());
    }

    @Override
    public void bridge$setCustomName(String name) {
        this.shadow$setCustomName(new TextComponent(name));
    }
}
