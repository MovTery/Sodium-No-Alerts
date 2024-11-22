package com.movtery.mixin;

import net.caffeinemc.mods.sodium.client.compatibility.checks.PostLaunchChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PostLaunchChecks.class)
public class PostLaunchChecksMixin {
    @Inject(method = "isUsingPojavLauncher", at = @At("RETURN"), cancellable = true)
    private static void isUsingPojavLauncher(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
