/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_2.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_15_2.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_15_2.forge.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeBrigadierCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return event.getCommandDispatcher();
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        event.getCommandDispatcher().register(node);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public CommandSender getSender(CommandSource source) {
        return new ForgeCommandSender(source);
    }

    @Override
    public Player getPlayer(CommandSource source) {
        return new ForgePlayer((PlayerEntity) source.getEntity());
    }

    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
