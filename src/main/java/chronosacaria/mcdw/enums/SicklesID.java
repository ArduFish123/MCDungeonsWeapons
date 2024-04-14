package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwSickle;
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

public enum SicklesID implements IMeleeWeaponID, IInnateEnchantment {
    SICKLE_LAST_LAUGH_GOLD(true, ToolMaterials.IRON,2, -2.1f, "minecraft:iron_ingot"),
    SICKLE_LAST_LAUGH_SILVER(true, ToolMaterials.IRON,2, -2.1f, "minecraft:iron_ingot"),
    SICKLE_NIGHTMARES_BITE(true, ToolMaterials.IRON,2, -2.1f, "minecraft:iron_ingot"),
    SICKLE_SICKLE(true, ToolMaterials.IRON,1, -2.1f, "minecraft:iron_ingot");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    SicklesID(boolean isEnabled, ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<SicklesID, McdwSickle> getItemsEnum() {
        return ItemsRegistry.SICKLE_ITEMS;
    }

    public static HashMap<SicklesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SICKLE_SPAWN_RATES;
    }

    public static HashMap<SicklesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.sickleStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.sickleStats.get(this).isEnabled;    }

    @Override
    public McdwSickle getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<SicklesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats.get(this);
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
            case SICKLE_LAST_LAUGH_GOLD, SICKLE_LAST_LAUGH_SILVER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.PROSPECTOR);
            case SICKLE_NIGHTMARES_BITE -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.POISON_CLOUD);
            case SICKLE_SICKLE -> Map.of();
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwSickle makeWeapon() {
        McdwSickle mcdwSickle = new McdwSickle(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwSickle);
        return mcdwSickle;
    }
}