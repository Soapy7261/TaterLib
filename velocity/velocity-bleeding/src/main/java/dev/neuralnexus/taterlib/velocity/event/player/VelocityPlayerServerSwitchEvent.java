package dev.neuralnexus.taterlib.velocity.event.player;

import com.velocitypowered.api.event.player.ServerConnectedEvent;

import dev.neuralnexus.taterlib.event.player.PlayerServerSwitchEvent;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/** Velocity implementation of {@link PlayerServerSwitchEvent}. */
public class VelocityPlayerServerSwitchEvent implements PlayerServerSwitchEvent {
    private final ServerConnectedEvent event;

    public VelocityPlayerServerSwitchEvent(ServerConnectedEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String toServer() {
        if (!event.getPlayer().getCurrentServer().isPresent()) return null;
        return event.getPlayer().getCurrentServer().get().getServerInfo().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String fromServer() {
        if (!event.getPreviousServer().isPresent()) return null;
        return event.getPreviousServer().get().getServerInfo().getName();
    }
}
