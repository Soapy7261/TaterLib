package dev.neuralnexus.taterlib.v1_14.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_14.fabric.event.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_14.fabric.player.FabricPlayer;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class FabricPlayerDeathEvent extends FabricEntityDeathEvent implements PlayerDeathEvent {
    private final PlayerEntity player;
    private final DamageSource source;

    public FabricPlayerDeathEvent(PlayerEntity player, DamageSource source) {
        super(player, source);
        this.player = player;
        this.source = source;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new FabricPlayer(player);
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        return source.getDeathMessage(player).getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {}

    /** {@inheritDoc} */
    @Override
    public boolean keepInventory() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
