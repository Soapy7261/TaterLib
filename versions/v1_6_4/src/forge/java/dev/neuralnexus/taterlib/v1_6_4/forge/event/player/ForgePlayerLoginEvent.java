/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;

/** Forge implementation of {@link PlayerLoginEvent}. */
public class ForgePlayerLoginEvent implements PlayerLoginEvent {
    private final EntityPlayer player;
    private String loginMessage = "";

    public ForgePlayerLoginEvent(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Player player() {
        return new ForgePlayer(player);
    }

    @Override
    public String loginMessage() {
        if (!this.loginMessage.isEmpty()) {
            return this.loginMessage;
        }
        return player.getCommandSenderName() + " joined the game";
    }

    @Override
    public void setLoginMessage(String message) {
        this.loginMessage = message;
    }
}
