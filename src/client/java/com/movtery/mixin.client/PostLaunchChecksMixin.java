package com.movtery.mixin.client;

import com.movtery.SodiumNoAlertsClient;
import me.jellysquid.mods.sodium.client.compatibility.checks.PostLaunchChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PostLaunchChecks.class)
public class PostLaunchChecksMixin {
    @Inject(method = "isUsingPojavLauncher", at = @At("RETURN"), cancellable = true)
    private static void isUsingPojavLauncher(CallbackInfoReturnable<Boolean> cir) {
        Boolean originalValue = cir.getReturnValue();
        boolean additionalCondition = SodiumNoAlertsClient.options().pojav.disablePojavLauncherWarnings && originalValue;

        cir.setReturnValue(additionalCondition);
    }
}
