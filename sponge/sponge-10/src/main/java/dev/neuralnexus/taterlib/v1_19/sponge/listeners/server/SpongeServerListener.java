/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_19.sponge.listeners.server;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartingEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppingEventImpl;

import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppedGameEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;

/** Listens to server events. */
public class SpongeServerListener {
    /**
     * Called when the server is starting.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStarting(StartingEngineEvent<Server> event) {
        ServerEvents.STARTING.invoke(new ServerStartingEventImpl());
    }

    /**
     * Called when the server has started.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStarted(StartedEngineEvent<Server> event) {
        ServerEvents.STARTED.invoke(new ServerStartedEventImpl());
    }

    /**
     * Called when the server is stopping.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEventImpl());
    }

    /**
     * Called when the server has stopped.
     *
     * @param event The event.
     */
    @Listener
    public void onServerStopped(StoppedGameEvent event) {
        ServerEvents.STOPPED.invoke(new ServerStoppedEventImpl());
    }
}
