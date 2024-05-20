package dev.neuralnexus.taterlib.v1_19_4.forge.event.server;

import dev.neuralnexus.taterlib.event.server.ServerStoppingEvent;

/** Forge implementation of {@link ServerStoppingEvent}. */
public class ForgeServerStoppingEvent extends ForgeServerEvent implements ServerStoppingEvent {
    public ForgeServerStoppingEvent(net.minecraftforge.event.server.ServerStoppingEvent event) {
        super(event);
    }
}
