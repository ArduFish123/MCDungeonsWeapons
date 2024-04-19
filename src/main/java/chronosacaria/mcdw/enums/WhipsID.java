package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwWhip;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
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

public enum WhipsID implements IMeleeWeaponID, IInnateEnchantment {
    WHIP_VINE_WHIP(true, ToolMaterials.IRON, 5, -3.1f, "minecraft:vine"),
    WHIP_WHIP(true, ToolMaterials.IRON, 3, -3.1f, "minecraft:string");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;
    @SuppressWarnings("SameParameterValue")
    WhipsID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<WhipsID, McdwWhip> getItemsEnum() {
        return ItemsRegistry.WHIP_ITEMS;
    }

    public static HashMap<WhipsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.WHIP_SPAWN_RATES;
    }

    public static HashMap<WhipsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.whipStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.whipStats.get(this).isEnabled;    }

    @Override
    public McdwWhip getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<WhipsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.whipStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.whipStats.get(this);
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
    public MeleeStats getMeleeStats() {
        return new IMeleeWeaponID.MeleeStats().meleeStats(isEnabled, CleanlinessHelper.materialToString(material), damage, attackSpeed, repairIngredient);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case WHIP_WHIP -> Map.of();
            case WHIP_VINE_WHIP -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.JUNGLE_POISON);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwWhip makeWeapon() {
        McdwWhip mcdwWhip = new McdwWhip(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwWhip);
        return mcdwWhip;
    }
}
