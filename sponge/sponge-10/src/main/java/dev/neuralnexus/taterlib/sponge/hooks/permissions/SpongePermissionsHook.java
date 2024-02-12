package dev.neuralnexus.taterlib.sponge.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.sponge.command.SpongeCommandSender;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import org.spongepowered.api.entity.living.player.server.ServerPlayer;

/** A hook for Sponge permissions */
public class SpongePermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "spongepermissions";
    }

    /**
     * Get if a sender has a permission
     *
     * @param commandSender The sender to check
     * @param permission The permission to check
     * @return If the sender has the permission
     */
    @Override
    public boolean hasPermission(CommandSender commandSender, String permission) {
        if (commandSender instanceof Player) {
            return ((ServerPlayer) ((SpongePlayer) commandSender).player())
                    .hasPermission(permission);
        } else {
            return (((SpongeCommandSender) commandSender).sender().hasPermission(permission));
        }
    }
}
