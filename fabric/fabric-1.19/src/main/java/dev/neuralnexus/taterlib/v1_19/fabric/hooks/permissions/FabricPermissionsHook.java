package dev.neuralnexus.taterlib.v1_19.fabric.hooks.permissions;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import me.lucko.fabric.api.permissions.v0.Permissions;

/** A hook for Fabric permissions */
public class FabricPermissionsHook implements PermissionsHook {
    /** {@inheritDoc} */
    @Override
    public String name() {
        return "fabricpermissions";
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
            return Permissions.check(((VanillaPlayer) commandSender).player(), permission, 4);
        } else {
            return Permissions.check(
                    ((VanillaCommandSender) commandSender).sender(), permission, 4);
        }
    }
}
