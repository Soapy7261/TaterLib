/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.event.player;

import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.Cancellable;

import java.util.Set;

/** Abstract class for player message events */
public interface PlayerMessageEvent extends PlayerEvent, Cancellable {
    /**
     * Getter for the message
     *
     * @return The message
     */
    String message();

    /**
     * Setter for the message
     *
     * @param message The message
     */
    void setMessage(String message);

    /**
     * Getter for the recipients
     *
     * @return The recipients
     */
    Set<User> recipients();

    /**
     * Setter for the recipients
     *
     * @param recipients The recipients
     */
    void setRecipients(Set<User> recipients);
}
