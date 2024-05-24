/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */


package dev.timefall.mcdw.configs.stats;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.configs.stats.enchantment_sections.McdwEnchantmentStats;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;

import java.util.function.Supplier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwEnchantmentStatsConfig extends Config {

    public static final McdwEnchantmentStatsConfig CONFIG = ConfigApi.registerAndLoadConfig((Supplier<McdwEnchantmentStatsConfig>) McdwEnchantmentStatsConfig::new);

    public McdwEnchantmentStatsConfig() {
        super(Mcdw.ID("mcdw_stats_config"));
    }

    private McdwEnchantmentStats mcdwEnchantmentStats = new McdwEnchantmentStats();


    public McdwEnchantmentStats getMcdwEnchantmentStats() {
        return mcdwEnchantmentStats;
    }
}
