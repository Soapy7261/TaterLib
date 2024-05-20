package dev.neuralnexus.taterlib.v1_19_4.forge.listeners.command;

import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.v1_19_4.forge.event.command.ForgeBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_19_4.forge.event.command.ForgeCommandRegisterEvent;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Forge register commands. */
public class ForgeCommandsListener {
    /**
     * Registers commands.
     *
     * @param event The register commands event.
     */
    @SubscribeEvent
    public void onRegisterBrigadierCommand(RegisterCommandsEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new ForgeBrigadierCommandRegisterEvent(event));
    }
}
