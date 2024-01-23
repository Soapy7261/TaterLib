package dev.neuralnexus.taterlib.vanilla.event.server;

import dev.neuralnexus.taterlib.event.server.ServerEvent;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.minecraft.server.MinecraftServer;

public class VanillaServerEvent implements ServerEvent {
    public VanillaServerEvent(MinecraftServer server) {
        new VanillaServer(server);
    }

    /** {@inheritDoc} */
    @Override
    public Server getServer() {
        return VanillaServer.getInstance();
    }
}
