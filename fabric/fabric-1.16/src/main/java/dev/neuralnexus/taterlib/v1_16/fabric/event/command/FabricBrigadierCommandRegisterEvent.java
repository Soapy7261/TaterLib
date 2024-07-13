/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.event.command;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_16.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.v1_16.fabric.entity.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

/** Fabric implementation of {@link BrigadierCommandRegisterEvent}. */
public class FabricBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<ServerCommandSource> {
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final boolean dedicated;

    public FabricBrigadierCommandRegisterEvent(
            CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        this.dispatcher = dispatcher;
        this.dedicated = dedicated;
    }

    @Override
    public boolean isDedicated() {
        return dedicated;
    }

    @Override
    public CommandDispatcher<ServerCommandSource> dispatcher() {
        return dispatcher;
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<ServerCommandSource> node,
            String commandName,
            String... aliases) {
        dispatcher.register(node);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public CommandSender getSender(ServerCommandSource source) {
        return new FabricCommandSender(source);
    }

    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer((PlayerEntity) source.getEntity());
    }

    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
