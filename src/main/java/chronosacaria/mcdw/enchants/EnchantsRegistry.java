package chronosacaria.mcdw.enchants;

import chronosacaria.mcdw.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment POISON_CLOUD;
    public static Enchantment THUNDERING;
    public static Enchantment GRAVITY;
    public static Enchantment EXPLODING;
    public static Enchantment FREEZING;
    public static Enchantment LEECHING;
    public static Enchantment STUNNING;

    public static void init() {
        POISON_CLOUD = new PoisonCloud(Enchantment.Rarity.COMMON, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        THUNDERING = new Thundering(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        GRAVITY = new Gravity(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        EXPLODING = new Exploding(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        FREEZING = new Freezing(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        LEECHING = new Leeching(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        STUNNING = new Stunning(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});

    }

}
