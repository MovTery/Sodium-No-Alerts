package com.movtery.mixin;

import com.movtery.gui.DisableWarningPages;
import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SodiumOptionsGUI.class)
public class SodiumOptionsGUIMixin {
    @Shadow @Final private List<OptionPage> pages;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(Screen prevScreen, CallbackInfo ci) {
        this.pages.add(DisableWarningPages.sodiumNoAlerts());
    }
}
