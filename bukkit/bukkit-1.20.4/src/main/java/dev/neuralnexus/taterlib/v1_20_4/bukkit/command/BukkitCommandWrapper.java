package dev.neuralnexus.taterlib.v1_20_4.bukkit.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.adapters.BukkitAdapter;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** Wraps a command callback into a Bukkit CommandExecutor. */
public class BukkitCommandWrapper implements CommandExecutor {
    private final Command.Callback callback;

    public BukkitCommandWrapper(Command.Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onCommand(
            CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            return callback.execute(
                    new VanillaPlayer(BukkitAdapter.get().getPlayer((Player) sender)), label, args);
        }
        return callback.execute(new BukkitCommandSender(sender), label, args);
    }
}
