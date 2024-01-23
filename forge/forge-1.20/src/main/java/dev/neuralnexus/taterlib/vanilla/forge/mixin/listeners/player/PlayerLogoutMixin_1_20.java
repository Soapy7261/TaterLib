package dev.neuralnexus.taterlib.vanilla.forge.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player logout listener. */
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class PlayerLogoutMixin_1_20 {
    @Shadow
    public abstract ServerPlayer getPlayer();

    /**
     * Called when a player logs out.
     *
     * @param reason The reason for the logout.
     * @param ci The callback info.
     */
    @Inject(method = "onDisconnect", at = @At("HEAD"))
    private void onLogout(Component reason, CallbackInfo ci) {
        PlayerEvents.LOGOUT.invoke(new VanillaPlayerLogoutEvent(this.getPlayer()));
    }
}
