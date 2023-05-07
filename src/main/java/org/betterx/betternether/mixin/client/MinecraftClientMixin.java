package org.betterx.betternether.mixin.client;

import org.betterx.betternether.config.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "<init>*", at = @At(value = "TAIL"))
    private void onInit(GameConfig args, CallbackInfo info) {
        Config.save();
    }
}
