/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs.stats.item_sections;

import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwGlaiveItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats glaiveCacklingBroom = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,   5,    -3f,  1.0d, 5,   new Identifier("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats glaiveGlaive        = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,   5,    -3f,  1.0d, 10,  new Identifier("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats glaiveGraveBane     = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,   6,    -3f,  1.0d, 5,   new Identifier("minecraft:gold_ingot"));
    private IMcdwWeaponStats.MeleeStats glaiveVenomGlaive   = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,   6,    -3f,  1.0d, 5,   new Identifier("minecraft:iron_ingot"));

    public IMcdwWeaponStats.MeleeStats getGlaiveCacklingBroom() {
        return glaiveCacklingBroom;
    }

    public IMcdwWeaponStats.MeleeStats getGlaiveGlaive() {
        return glaiveGlaive;
    }

    public IMcdwWeaponStats.MeleeStats getGlaiveGraveBane() {
        return glaiveGraveBane;
    }

    public IMcdwWeaponStats.MeleeStats getGlaiveVenomGlaive() {
        return glaiveVenomGlaive;
    }

}
