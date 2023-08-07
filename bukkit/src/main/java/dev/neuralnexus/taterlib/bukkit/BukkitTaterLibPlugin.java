package dev.neuralnexus.taterlib.bukkit;

import dev.neuralnexus.taterlib.bukkit.commands.BukkitTaterLibCommand;
import dev.neuralnexus.taterlib.bukkit.listeners.player.BukkitPlayerListener;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import org.bukkit.plugin.PluginManager;

/**
 * The TaterLib Bukkit plugin.
 */
public class BukkitTaterLibPlugin extends TemplateBukkitPlugin implements TaterLibPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        if (getServer().getPluginManager().getPlugin("LuckPerms") != null) {
            useLogger("LuckPerms detected, enabling LuckPerms hook.");
            TaterLib.addHook(new LuckPermsHook());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BukkitPlayerListener(), this);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        getCommand(TaterLibCommand.getCommandName()).setExecutor(new BukkitTaterLibCommand());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onEnable() {
        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        pluginStop();
    }
}
