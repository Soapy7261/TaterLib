package dev.neuralnexus.taterlib.vanilla.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.vanilla.inventory.VanillaPlayerInventory;

import io.netty.buffer.Unpooled;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

import java.util.UUID;

/** Vanilla implementation of {@link Player}. */
public class VanillaPlayer extends VanillaEntity implements Player {
    private final net.minecraft.world.entity.player.Player player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The player.
     */
    public VanillaPlayer(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The player.
     * @param serverName The server name.
     */
    public VanillaPlayer(net.minecraft.world.entity.player.Player player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the player
     *
     * @return The player
     */
    public net.minecraft.world.entity.player.Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String getIPAddress() {
        return ((ServerPlayer) player).getIpAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getServerName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.displayClientMessage(Component.nullToEmpty(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        String[] channelParts = channel.split(":");
        if (channelParts.length == 1) {
            channelParts = new String[] {"tl-user-forgot", channelParts[0]};
        }
        ResourceLocation resourceLocation = new ResourceLocation(channelParts[0], channelParts[1]);
        ((ServerPlayer) player)
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                resourceLocation,
                                new FriendlyByteBuf(Unpooled.wrappedBuffer(data))));
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new VanillaPlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayer) player).latency;
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).connection.disconnect(Component.nullToEmpty(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Abstract world information
        ResourceKey<Level> dimension =
                ResourceKey.create(
                        Registries.DIMENSION,
                        new ResourceLocation(location.getWorld().split(":")[1]));
        ((ServerPlayer) player)
                .setRespawnPosition(
                        dimension,
                        new BlockPos(
                                (int) location.getX(),
                                (int) location.getY(),
                                (int) location.getZ()),
                        0,
                        forced,
                        false);
    }

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromName(((ServerPlayer) player).gameMode.getGameModeForPlayer().getName());
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {
        ((ServerPlayer) player).setGameMode(GameType.byId(gameMode.getId()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissions(permissionLevel);
    }
}
