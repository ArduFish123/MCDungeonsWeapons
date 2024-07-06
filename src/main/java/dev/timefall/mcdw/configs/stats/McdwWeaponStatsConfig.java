/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */


package dev.timefall.mcdw.configs.stats;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.configs.stats.item_sections.*;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwWeaponStatsConfig extends Config {

    public static final McdwWeaponStatsConfig CONFIG = ConfigApi.registerAndLoadConfig(McdwWeaponStatsConfig::new);

    public McdwWeaponStatsConfig() {
        super(Mcdw.ID("mcdw_weapon_stats_config"));
    }

    transient private LinkedHashMap<Identifier, Supplier<Boolean>> itemEnabledMap = new LinkedHashMap<>();

    @ApiStatus.Internal
    public void registerItemEnableCheck(Identifier id, Supplier<Boolean> checker){
        itemEnabledMap.put(id,checker);
    }

    public boolean isItemEnabled(Identifier id) {
        //supplier returns isEnabled, we negate that for disabled check
        return itemEnabledMap.getOrDefault(id, () -> false).get();
    }

    private McdwAxeItemStats mcdwAxeItemStats = new McdwAxeItemStats();
    private McdwBowItemStats mcdwBowItemStats = new McdwBowItemStats();
    private McdwCrossbowItemStats mcdwCrossbowItemStats = new McdwCrossbowItemStats();
    private McdwDaggerItemStats mcdwDaggerItemStats = new McdwDaggerItemStats();
    private McdwDoubleAxeItemStats mcdwDoubleAxeItemStats = new McdwDoubleAxeItemStats();
    private McdwGauntletItemStats mcdwGauntletItemStats = new McdwGauntletItemStats();
    private McdwGlaiveItemStats mcdwGlaiveItemStats = new McdwGlaiveItemStats();
    private McdwHammerItemStats mcdwHammerItemStats = new McdwHammerItemStats();
    private McdwLongbowItemStats mcdwLongbowItemStats = new McdwLongbowItemStats();
    private McdwPickaxeItemStats mcdwPickaxeItemStats = new McdwPickaxeItemStats();
    private McdwScytheItemStats mcdwScytheItemStats = new McdwScytheItemStats();
    private McdwShieldItemStats mcdwShieldItemStats = new McdwShieldItemStats();
    private McdwShortbowItemStats mcdwShortbowItemStats = new McdwShortbowItemStats();
    private McdwSickleItemStats mcdwSickleItemStats = new McdwSickleItemStats();
    private McdwSoulDaggerItemStats mcdwSoulDaggerItemStats = new McdwSoulDaggerItemStats();
    private McdwSpearItemStats mcdwSpearItemStats = new McdwSpearItemStats();
    private McdwStaffItemStats mcdwStaffItemStats = new McdwStaffItemStats();
    private McdwSwordItemStats mcdwSwordItemStats = new McdwSwordItemStats();
    private McdwWhipItemStats mcdwWhipItemStats = new McdwWhipItemStats();


    public McdwAxeItemStats getAxeItemStats() {
        return mcdwAxeItemStats;
    }

    public McdwBowItemStats getBowItemStats() {
        return mcdwBowItemStats;
    }

    public McdwCrossbowItemStats getCrossbowItemStats() {
        return mcdwCrossbowItemStats;
    }

    public McdwDaggerItemStats getDaggerItemStats() {
        return mcdwDaggerItemStats;
    }

    public McdwDoubleAxeItemStats getDoubleAxeItemStats() {
        return mcdwDoubleAxeItemStats;
    }

    public McdwGauntletItemStats getGauntletItemStats() {
        return mcdwGauntletItemStats;
    }

    public McdwGlaiveItemStats getGlaiveItemStats() {
        return mcdwGlaiveItemStats;
    }

    public McdwHammerItemStats getHammerItemStats() {
        return mcdwHammerItemStats;
    }

    public McdwLongbowItemStats getLongbowItemStats() {
        return mcdwLongbowItemStats;
    }

    public McdwPickaxeItemStats getPickaxeItemStats() {
        return mcdwPickaxeItemStats;
    }

    public McdwScytheItemStats getScytheItemStats() {
        return mcdwScytheItemStats;
    }

    public McdwShieldItemStats getShieldItemStats() {
        return mcdwShieldItemStats;
    }

    public McdwShortbowItemStats getShortbowItemStats() {
        return mcdwShortbowItemStats;
    }

    public McdwSickleItemStats getSickleItemStats() {
        return mcdwSickleItemStats;
    }

    public McdwSoulDaggerItemStats getSoulDaggerItemStats() {
        return mcdwSoulDaggerItemStats;
    }

    public McdwSpearItemStats getSpearItemStats() {
        return mcdwSpearItemStats;
    }

    public McdwStaffItemStats getStaffItemStats() {
        return mcdwStaffItemStats;
    }

    public McdwSwordItemStats getSwordItemStats() {
        return mcdwSwordItemStats;
    }

    public McdwWhipItemStats getWhipItemStats() {
        return mcdwWhipItemStats;
    }
}