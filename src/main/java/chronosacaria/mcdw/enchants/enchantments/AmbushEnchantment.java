/*
Timefall Development License 1.2
Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwCustomWeaponBase;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import chronosacaria.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class AmbushEnchantment extends DamageBoostEnchantment {

    public AmbushEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsEnabled()) {
            ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
                // For loop creates first 3 levels of enchanted books
                for (int i = 1; i <= getMaxLevel(); i++)
                    entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
            });
        }
    }

    @Override
    public int getMaxLevel(){
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getMaxLevel();
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING)
                || !(other instanceof AOEEnchantment || other instanceof DamageBoostEnchantment)
                || other.isAcceptableItem(DaggersID.DAGGER_SWIFT_STRIKER.getItem().getDefaultStack());
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsEnabled()
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsAvailableForRandomSelection();
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsEnabled()
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsAvailableForEnchantedBookOffer();
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem
                || stack.getItem() instanceof AxeItem
                || stack.getItem() instanceof McdwCustomWeaponBase
                || stack.isOf(DaggersID.DAGGER_SWIFT_STRIKER.getItem());
    }


}

