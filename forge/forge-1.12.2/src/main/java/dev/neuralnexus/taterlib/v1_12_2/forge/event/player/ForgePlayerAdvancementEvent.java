package dev.neuralnexus.taterlib.v1_12_2.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_12_2.forge.player.ForgePlayer;

import net.minecraftforge.event.entity.player.AdvancementEvent;

import java.util.Collection;

/** Forge implementation of {@link PlayerAdvancementEvent}. */
public class ForgePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public ForgePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String advancement() {
        if (event.getAdvancement().getDisplay() != null) {
            return event.getAdvancement().getDisplay().getTitle().getFormattedText();
        }
        if (event.getAdvancement().getParent() != null
                && event.getAdvancement().getParent().getDisplay() != null) {
            return this.event
                    .getAdvancement()
                    .getParent()
                    .getDisplay()
                    .getTitle()
                    .getFormattedText();
        } else {
            return "";
        }
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer(event.getEntityPlayer());
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent event) {
            super(event);
        }
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent event;

        public AdvancementProgress(AdvancementEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public Collection<String> criterion() {
            return this.event.getAdvancement().getCriteria().keySet();
        }
    }
}
