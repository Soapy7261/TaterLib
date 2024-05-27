package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerEvent}. */
public class BukkitServerEvent implements ServerEvent {
    private final org.bukkit.event.server.ServerEvent event;

    public BukkitServerEvent(org.bukkit.event.server.ServerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new BukkitServer(Bukkit.getServer());
    }
}
