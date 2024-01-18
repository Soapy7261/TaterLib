package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.event.api.*;
import dev.neuralnexus.taterlib.event.plugin.CommonPluginEnableEvent;
import dev.neuralnexus.taterlib.fabric.event.command.FabricBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterlib.vanilla.server.VanillaServer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import org.apache.logging.log4j.LogManager;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void platformInit(Object plugin, Object logger) {
        TaterAPIProvider.register(
                FabricLoader.getInstance()
                        .getModContainer("minecraft")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString());
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        pluginStart(
                this,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get(ServerType.FABRIC);
        api.setIsModLoaded((modId) -> FabricLoader.getInstance().isModLoaded(modId));
        api.setServer(VanillaServer::getInstance);

        // Initialize plugin data
        //        ServerLifecycleEvents.SERVER_STARTING.register(
        //                server -> FabricTaterLibPlugin.server = server);
        //        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());

        // Register Fabric API command events
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    CommandEvents.REGISTER_COMMAND.invoke(
                            new FabricCommandRegisterEvent(
                                    dispatcher, registryAccess, environment));
                    CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                            new FabricBrigadierCommandRegisterEvent(
                                    dispatcher, registryAccess, environment));
                });

        // Register Fabric API player events
        //        ServerPlayConnectionEvents.JOIN.register(
        //                (handler, sender, server) ->
        //                        PlayerEvents.LOGIN.invoke(
        //                                new FabricPlayerLoginEvent(handler, sender, server)));
        //        ServerPlayConnectionEvents.DISCONNECT.register(
        //                (handler, server) ->
        //                        PlayerEvents.LOGOUT.invoke(new FabricPlayerLogoutEvent(handler,
        // server)));

        // Register Fabric API server events
        //        ServerLifecycleEvents.SERVER_STARTING.register(
        //                server -> ServerEvents.STARTING.invoke(new
        // FabricServerStartingEvent(server)));
        //        ServerLifecycleEvents.SERVER_STARTED.register(
        //                server -> ServerEvents.STARTED.invoke(new
        // FabricServerStartedEvent(server)));
        //        ServerLifecycleEvents.SERVER_STOPPING.register(
        //                server -> ServerEvents.STOPPING.invoke(new
        // FabricServerStoppingEvent(server)));
        //        ServerLifecycleEvents.SERVER_STOPPED.register(
        //                server -> ServerEvents.STOPPED.invoke(new
        // FabricServerStoppedEvent(server)));

        // Register TaterLib Block events
        //        FabricBlockEvents.BLOCK_BREAK.register(
        //                (level, player, blockPos, blockState, blockEntity, itemStack, ci) ->
        //                        BlockEvents.BLOCK_BREAK.invoke(
        //                                new FabricBlockBreakEvent(
        //                                        level,
        //                                        player,
        //                                        blockPos,
        //                                        blockState,
        //                                        blockEntity,
        //                                        itemStack,
        //                                        ci)));

        // Register TaterLib Entity events
        //        FabricEntityEvents.DAMAGE.register(
        //                (entity, damageSource, damage, ci) ->
        //                        EntityEvents.DAMAGE.invoke(
        //                                new FabricEntityDamageEvent(entity, damageSource, damage,
        // ci)));
        //        FabricEntityEvents.DEATH.register(
        //                (entity, source) ->
        //                        EntityEvents.DEATH.invoke(new FabricEntityDeathEvent(entity,
        // source)));
        //        FabricEntityEvents.SPAWN.register(
        //                (entity, cir) ->
        //                        EntityEvents.SPAWN.invoke(new FabricEntitySpawnEvent(entity,
        // cir)));

        // Register TaterLib Player events
        //        FabricPlayerEvents.ADVANCEMENT_FINISHED.register(
        //                (player, advancement) ->
        //                        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
        //                                new FabricPlayerAdvancementEvent.AdvancementFinished(
        //                                        player, advancement)));
        //        FabricPlayerEvents.ADVANCEMENT_PROGRESS.register(
        //                (player, advancement, criterionName) ->
        //                        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
        //                                new FabricPlayerAdvancementEvent.AdvancementProgress(
        //                                        player, advancement, criterionName)));
        //        FabricPlayerEvents.DEATH.register(
        //                (player, source) ->
        //                        PlayerEvents.DEATH.invoke(new FabricPlayerDeathEvent(player,
        // source)));
        //        FabricPlayerEvents.MESSAGE.register(
        //                (player, message, ci) ->
        //                        PlayerEvents.MESSAGE.invoke(
        //                                new FabricPlayerMessageEvent(player, message, ci)));
        //        FabricPlayerEvents.RESPAWN.register(
        //                ((player, alive) ->
        //                        PlayerEvents.RESPAWN.invoke(new FabricPlayerRespawnEvent(player,
        // alive))));
    }

    @Override
    public void platformEnable() {
        PluginEvents.ENABLED.invoke(new CommonPluginEnableEvent());
    }
}
