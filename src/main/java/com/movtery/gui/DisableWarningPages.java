package com.movtery.gui;

import com.google.common.collect.ImmutableList;
import com.movtery.gui.storage.SodiumNoAlertsOptionsStorage;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class DisableWarningPages {
    public static final SodiumNoAlertsOptionsStorage sodiumNoAlertsOpts = new SodiumNoAlertsOptionsStorage();

    public static OptionPage sodiumNoAlerts() {
        List<OptionGroup> groups = new ArrayList<>();

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumNoAlertsOpts)
                        .setName(Component.translatable("sodium.options.check_pojav_launcher"))
                        .setTooltip(Component.translatable("sodium.options.check_pojav_launcher.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.sodiumNoAlertsSettings.disablePojavLauncherWarnings = value,
                                opts -> opts.sodiumNoAlertsSettings.disablePojavLauncherWarnings)
                        .build())
                .build());

        return new OptionPage(Component.translatable("sodium.options.pages.sodium_no_alerts"), ImmutableList.copyOf(groups));
    }
}
