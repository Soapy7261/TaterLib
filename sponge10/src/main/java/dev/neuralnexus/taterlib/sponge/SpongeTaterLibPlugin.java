package dev.neuralnexus.taterlib.sponge;

import com.google.inject.Inject;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.sponge.listeners.block.SpongeBlockListener;
import dev.neuralnexus.taterlib.sponge.listeners.command.SpongeCommandListener;
import dev.neuralnexus.taterlib.sponge.listeners.entity.SpongeEntityListener;
import dev.neuralnexus.taterlib.sponge.listeners.player.SpongePlayerListener;
import dev.neuralnexus.taterlib.sponge.listeners.server.SpongeServerListener;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterlib.sponge.server.SpongeServer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/**
 * The TaterLib Sponge plugin.
 */
@Plugin(TaterLib.Constants.PROJECT_ID)
public class SpongeTaterLibPlugin implements TaterLibPlugin {
    @Inject
    public SpongeTaterLibPlugin(Logger logger, PluginContainer container) {
        TaterAPIProvider.register(Sponge.platform().container(Platform.Component.GAME).metadata().version().toString());
        pluginStart(container, new SpongeLogger(logger));
        TaterAPI api = TaterAPIProvider.get(ServerType.SPONGE);
        api.setIsPluginLoaded((plugin) -> Sponge.pluginManager().plugin(plugin).isPresent());
        api.setServer(() -> new SpongeServer(Sponge.server()));

        // Register listeners
        EventManager eventManager = Sponge.eventManager();
        eventManager.registerListeners(container, new SpongeBlockListener());
        eventManager.registerListeners(container, new SpongeCommandListener());
        eventManager.registerListeners(container, new SpongeEntityListener());
        eventManager.registerListeners(container, new SpongePlayerListener());
        eventManager.registerListeners(container, new SpongeServerListener());
    }

    /**
     * Fired when the server stops.
     * @param event The event
     */
    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        pluginStop();
    }
}
