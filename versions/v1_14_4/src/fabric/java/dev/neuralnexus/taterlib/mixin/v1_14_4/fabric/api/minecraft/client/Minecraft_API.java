/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.fabric.api.minecraft.client;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.client.MinecraftBridge;

import io.netty.buffer.Unpooled;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.stream.Collectors;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V19_4)
@Mixin(Minecraft.class)
@Implements(@Interface(iface = SimpleServer.class, prefix = "server$", remap = Remap.NONE))
public abstract class Minecraft_API implements MinecraftBridge {
    @Shadow public LocalPlayer player;

    public String server$brand() {
        if (this.player == null) return "Local";
        String brand = this.player.getServerBrand();
        return brand == null ? "Local" : brand;
    }

    public List<User> server$onlinePlayers() {
        return this.player.connection.getOnlinePlayers().stream()
                .map(PlayerInfo::getProfile)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    void server$sendPacket(ResourceKey channel, byte[] data) {
        ResourceLocation id = (ResourceLocation) channel;
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeBytes(data);
        this.player.connection.send(new ServerboundCustomPayloadPacket(id, byteBuf));
    }

    void server$broadcastMessage(String message) {
        this.bridge$broadcastMessage(message);
    }
}
