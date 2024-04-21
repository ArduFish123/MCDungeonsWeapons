
/*
Timefall Development License 1.2
Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.bases;

import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.RarityHelper;
import chronosacaria.mcdw.enums.LongbowsID;
import chronosacaria.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class McdwLongbow extends BowItem implements IInnateEnchantment {

    public final ToolMaterial material;
    public final float drawSpeed;
    public float maxBowRange;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final ParticleEffect type;
    String[] repairIngredient;
    LongbowsID longbowsEnum;

    public McdwLongbow(LongbowsID longbowsEnum, ToolMaterial material, float drawSpeed, float maxBowRangePar, String[] repairIngredient) {
        super(new Item.Settings().maxCount(1).maxDamage(material.getDurability())
                .rarity(RarityHelper.fromToolMaterial(material))
        );
        this.longbowsEnum = longbowsEnum;
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.RANGED).register(entries -> entries.add(this.getDefaultStack()));
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.repairIngredient = repairIngredient;
        this.maxBowRange = maxBowRangePar;
        type = null;
    }

    public float getDrawSpeed() {
        return Math.max(0, drawSpeed);
    }

    @Override
    public int getRange() {
        return (int) maxBowRange;
    }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return CleanlinessHelper.canRepairCheck(repairIngredient, ingredient);
    }

    @Override
    public ItemStack getDefaultStack() {
        return getInnateEnchantedStack(this);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        if (this.longbowsEnum.getIsEnabled())
            return this.longbowsEnum.getInnateEnchantments();
        return Map.of();
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(stack, world, tooltip, tooltipContext);
        CleanlinessHelper.mcdw$tooltipHelper(stack, tooltip, 14);
    }
}