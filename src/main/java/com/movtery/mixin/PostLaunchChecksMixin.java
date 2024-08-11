package com.movtery.mixin;

import com.movtery.SodiumNoAlerts;
import me.jellysquid.mods.sodium.client.compatibility.checks.LateDriverScanner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LateDriverScanner.class)
public class PostLaunchChecksMixin {
    @Inject(method = "isUsingPojavLauncher", at = @At("RETURN"), cancellable = true)
    private static void isUsingPojavLauncher(CallbackInfoReturnable<Boolean> cir) {
        boolean disable = SodiumNoAlerts.options().sodiumNoAlertsSettings.disablePojavLauncherWarnings;
        cir.setReturnValue(!disable && cir.getReturnValue());
    }
}
