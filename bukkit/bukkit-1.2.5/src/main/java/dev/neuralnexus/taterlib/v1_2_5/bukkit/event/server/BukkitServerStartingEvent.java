package dev.neuralnexus.taterlib.v1_2_5.bukkit.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.server.SimpleServer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.server.BukkitServer;

import org.bukkit.Bukkit;

/** Bukkit implementation of {@link ServerStartingEvent}. */
public class BukkitServerStartingEvent implements ServerStartingEvent {
    /** {@inheritDoc} */
    @Override
    public SimpleServer server() {
        return new BukkitServer(Bukkit.getServer());
    }
}
