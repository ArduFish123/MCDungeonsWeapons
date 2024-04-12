package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.bases.McdwSoulDagger;
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

public enum SoulDaggersID implements IMeleeWeaponID, IInnateEnchantment {
    SOUL_DAGGER_ETERNAL_KNIFE(ToolMaterials.NETHERITE,4, -2.8f, "minecraft:netherite_scrap"),
    SOUL_DAGGER_SOUL_KNIFE(ToolMaterials.IRON,4, -2.8f, "minecraft:iron_ingot"),
    SOUL_DAGGER_TRUTHSEEKER(ToolMaterials.NETHERITE,3, -2.8f, "minecraft:netherite_scrap");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;


    @SuppressWarnings("SameParameterValue")
    SoulDaggersID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<SoulDaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.SOUL_DAGGERS_ENABLED;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<SoulDaggersID, McdwSoulDagger> getItemsEnum() {
        return ItemsRegistry.SOUL_DAGGER_ITEMS;
    }

    public static HashMap<SoulDaggersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SOUL_DAGGER_SPAWN_RATES;
    }

    public static HashMap<SoulDaggersID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.soulDaggerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSoulDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<SoulDaggersID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.soulDaggerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.soulDaggerStats.get(this);
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
            case SOUL_DAGGER_ETERNAL_KNIFE -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.SOUL_SIPHON), 1);
            case SOUL_DAGGER_SOUL_KNIFE -> null;
            case SOUL_DAGGER_TRUTHSEEKER -> Map.of(EnchantsRegistry.enchantments.get(EnchantmentsID.COMMITTED), 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwSoulDagger makeWeapon() {
        McdwSoulDagger mcdwSoulDagger = new McdwSoulDagger(this, ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwSoulDagger);
        return mcdwSoulDagger;
    }
}