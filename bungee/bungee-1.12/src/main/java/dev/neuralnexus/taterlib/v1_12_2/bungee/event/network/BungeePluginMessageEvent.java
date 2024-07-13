/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.bungee.event.network;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_12_2.bungee.entity.player.BungeePlayer;
import dev.neuralnexus.taterlib.v1_12_2.bungee.server.BungeeServer;

import net.md_5.bungee.api.connection.ProxiedPlayer;

/** Bungee implementation of {@link PluginMessageEvent}. */
public class BungeePluginMessageEvent implements PluginMessageEvent {
    private final net.md_5.bungee.api.event.PluginMessageEvent event;

    public BungeePluginMessageEvent(net.md_5.bungee.api.event.PluginMessageEvent event) {
        this.event = event;
    }

    @Override
    public ResourceKey channel() {
        return ResourceKey.of(event.getTag());
    }

    @Override
    public byte[] data() {
        return event.getData();
    }

    /** Bungee implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends BungeePluginMessageEvent
            implements PluginMessageEvent.Player {
        private final net.md_5.bungee.api.event.PluginMessageEvent event;

        public Player(net.md_5.bungee.api.event.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

            @Override
        public ProxyPlayer player() {
            return new BungeePlayer((ProxiedPlayer) event.getReceiver());
        }
    }

    /** Bungee implementation of {@link PluginMessageEvent.Server}. */
    public static class Server extends BungeePluginMessageEvent
            implements PluginMessageEvent.Server {
        private final net.md_5.bungee.api.event.PluginMessageEvent event;

        public Server(net.md_5.bungee.api.event.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

            @Override
        public dev.neuralnexus.taterapi.server.Server server() {
            return new BungeeServer(
                    ((net.md_5.bungee.api.connection.Server) event.getReceiver()).getInfo());
        }
    }
}
