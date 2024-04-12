package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.bases.McdwGlaive;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GlaivesID implements IMeleeWeaponID, IInnateEnchantment {
    GLAIVE_CACKLING_BROOM(ToolMaterials.IRON,5, -3f, "minecraft:iron_ingot"),
    GLAIVE_GLAIVE(ToolMaterials.IRON,5, -3f, "minecraft:iron_ingot"),
    GLAIVE_GRAVE_BANE(ToolMaterials.IRON,6, -3f, "minecraft:iron_ingot"),
    GLAIVE_VENOM_GLAIVE(ToolMaterials.IRON,6, -3f, "minecraft:iron_ingot");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    GlaivesID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<GlaivesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.GLAIVES_ENABLED;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<GlaivesID, McdwGlaive> getItemsEnum() {
        return ItemsRegistry.GLAIVE_ITEMS;
    }

    public static HashMap<GlaivesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.GLAIVE_SPAWN_RATES;
    }

    public static HashMap<GlaivesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.glaiveStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGlaive getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<GlaivesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial(){
        return material;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public float getAttackSpeed(){
        return attackSpeed;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case GLAIVE_CACKLING_BROOM, GLAIVE_GRAVE_BANE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.SMITING), 1);
            case GLAIVE_GLAIVE -> null;
            case GLAIVE_VENOM_GLAIVE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.POISON_CLOUD), 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwGlaive makeWeapon() {
        McdwGlaive mcdwGlaive = new McdwGlaive(this, ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwGlaive);
        return mcdwGlaive;
    }
}