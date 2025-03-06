/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(EntityPlayer.class)
public class PlayerAdvancementFinishedMixin {
    @Inject(method = "triggerAchievement", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(StatBase statBase, CallbackInfo ci) {
        if (statBase.isAchievement()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(new VanillaPlayerAdvancementEvent.AdvancementFinished((EntityPlayer) (Object) this, (Achievement) statBase));
        }
    }
}
