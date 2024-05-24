/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.mcdw;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getPossibleEntries", at = @At("RETURN"))
    private static void mcdw$getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        var currentEntries = cir.getReturnValue();

        // 1. REMOVING ENCHANT ENTRIES ADDED INCORRECTLY

        var toRemove = new ArrayList<EnchantmentLevelEntry>();
        for (var entry: currentEntries) {
            if (!entry.enchantment.isAcceptableItem(stack)) {
                toRemove.add(entry);
            }
        }
        currentEntries.removeAll(toRemove);

        // 2. ADDING ENCHANT ENTRIES LEFT OUT INITIALLY

        // This logic is mostly copied from EnchantmentHelper.getPossibleEntries
        boolean isBook = stack.isOf(Items.BOOK);
        for (Enchantment enchantment : Registries.ENCHANTMENT) {
            // Don't check entries already added
            boolean alreadyAdded = currentEntries.stream().anyMatch(entry -> entry.enchantment.equals(enchantment));
            if (alreadyAdded) { continue; }

            if (enchantment.isTreasure()
                    && !treasureAllowed
                    || !enchantment.isAvailableForRandomSelection()
                    || !enchantment.isAcceptableItem(stack) // Custom logic, replacing `!enchantment.type.isAcceptableItem(item)`
                    && !isBook) continue;
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
                currentEntries.add(new EnchantmentLevelEntry(enchantment, i));
            }
        }
    }
}
