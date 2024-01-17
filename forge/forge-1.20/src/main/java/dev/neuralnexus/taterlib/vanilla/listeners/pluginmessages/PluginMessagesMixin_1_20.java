package dev.neuralnexus.taterlib.vanilla.listeners.pluginmessages;

import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.vanilla.event.pluginmessages.VanillaPluginMessageEvent_1_20;

import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerGamePacketListenerImpl.class)
public class PluginMessagesMixin_1_20 {
    @Shadow public ServerPlayer player;

    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent_1_20(packet));
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent_1_20.Player(packet, this.player));
    }
}
