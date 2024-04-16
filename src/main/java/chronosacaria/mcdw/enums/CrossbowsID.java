package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum CrossbowsID implements IRangedWeaponID, IInnateEnchantment {
    CROSSBOW_AUTO_CROSSBOW(          true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_AZURE_SEEKER(           true, ToolMaterials.IRON,      10, 28, 8.4f,  "minecraft:iron_ingot"),
    CROSSBOW_BABY_CROSSBOW(          true, ToolMaterials.IRON,      8,  23, 7.2f,  "minecraft:iron_ingot"),
    CROSSBOW_BURST_CROSSBOW(         true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_BUTTERFLY_CROSSBOW(     true, ToolMaterials.IRON,      10, 28, 8.9f,  "minecraft:iron_ingot"),
    CROSSBOW_COG_CROSSBOW(           true, ToolMaterials.IRON,      10, 28, 8.4f,  "minecraft:iron_ingot"),
    CROSSBOW_CORRUPTED_CROSSBOW(     true, ToolMaterials.NETHERITE, 16, 22, 14.0f, "minecraft:netherite_scrap"),
    CROSSBOW_DOOM_CROSSBOW(          true, ToolMaterials.NETHERITE, 9,  26, 8.0f,  "minecraft:netherite_scrap"),
    CROSSBOW_DUAL_CROSSBOW(          true, ToolMaterials.IRON,      8,  24, 7.0f,  "minecraft:iron_ingot"),
    CROSSBOW_EXPLODING_CROSSBOW(     true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_FERAL_SOUL_CROSSBOW(    true, ToolMaterials.IRON,      10, 28, 9.2f,  "minecraft:iron_ingot"),
    CROSSBOW_FIREBOLT_THROWER(       true, ToolMaterials.IRON,      9,  28, 7.9f,  "minecraft:iron_ingot"),
    CROSSBOW_HARPOON_CROSSBOW(       true, ToolMaterials.IRON,      12, 28, 11.0f, "minecraft:iron_ingot"),
    CROSSBOW_HARP_CROSSBOW(          true, ToolMaterials.IRON,      10, 28, 8.6f,  "minecraft:iron_ingot"),
    CROSSBOW_HEAVY_CROSSBOW(         true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_IMPLODING_CROSSBOW(     true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_LIGHTNING_HARP_CROSSBOW(true, ToolMaterials.DIAMOND,   16, 28, 14.2f, "minecraft:diamond"),
    CROSSBOW_NAUTICAL_CROSSBOW(      true, ToolMaterials.DIAMOND,   16, 24, 14.0f, "minecraft:diamond"),
    CROSSBOW_PRIDE_OF_THE_PIGLINS(   true, ToolMaterials.NETHERITE, 15, 20, 13.0f, "minecraft:netherite_scrap"),
    CROSSBOW_RAPID_CROSSBOW(         true, ToolMaterials.IRON,      9,  20, 8.2f,  "minecraft:iron_ingot"),
    CROSSBOW_SCATTER_CROSSBOW(       true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_SHADOW_CROSSBOW(        true, ToolMaterials.DIAMOND,   14, 25, 12.0f, "minecraft:diamond"),
    CROSSBOW_SLAYER_CROSSBOW(        true, ToolMaterials.DIAMOND,   10, 26, 8.8f,  "minecraft:diamond"),
    CROSSBOW_SOUL_CROSSBOW(          true, ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_SOUL_HUNTER_CROSSBOW(   true, ToolMaterials.DIAMOND,   12, 28, 11.0f, "minecraft:diamond"),
    CROSSBOW_SPELLBOUND_CROSSBOW(    true, ToolMaterials.IRON,      10, 28, 8.9f,  "minecraft:iron_ingot"),
    CROSSBOW_THE_SLICER(             true, ToolMaterials.IRON,      12, 28, 10.2f, "minecraft:iron_ingot"),
    CROSSBOW_VEILED_CROSSBOW(        true, ToolMaterials.DIAMOND,   16, 22, 14.5f, "minecraft:diamond"),
    CROSSBOW_VOIDCALLER_CROSSBOW(    true, ToolMaterials.DIAMOND,   14, 26, 12.5f, "minecraft:diamond");

    private final boolean isEnabled;
    private final ToolMaterial material;
    private final double projectileDamage;
    private final int drawSpeed;
    private final float range;
    private final String[] repairIngredient;

    CrossbowsID(boolean isEnabled, ToolMaterial material, double projectileDamage, int drawSpeed, float range, String... repairIngredient) {
        this.isEnabled = isEnabled;
        this.material = material;
        if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
            this.projectileDamage = projectileDamage;
        } else {
            this.projectileDamage = 0;
        }
        this.drawSpeed = drawSpeed;
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<CrossbowsID, McdwCrossbow> getItemsEnum() {
        return ItemsRegistry.CROSSBOW_ITEMS;
    }

    public static HashMap<CrossbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.CROSSBOW_SPAWN_RATES;
    }

    public static HashMap<CrossbowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.crossbowStats;
    }

    @Override
    public boolean getIsEnabled(){
        return CONFIG.mcdwNewStatsConfig.crossbowStats.get(this).isEnabled;
    }

    @Override
    public McdwCrossbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<CrossbowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.crossbowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.crossbowStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public double getProjectileDamage() {
        if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
            return projectileDamage;
        } else {
            return 0;
        }
    }

    @Override
    public int getDrawSpeed() {
        return drawSpeed;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public RangedStats getRangedStats() {
        return new IRangedWeaponID.RangedStats().rangedStats(isEnabled, CleanlinessHelper.materialToString(material), projectileDamage, drawSpeed, range, repairIngredient);
    }

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case CROSSBOW_AUTO_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.ACCELERATE);
            case CROSSBOW_AZURE_SEEKER, CROSSBOW_BURST_CROSSBOW, CROSSBOW_COG_CROSSBOW, CROSSBOW_DUAL_CROSSBOW, CROSSBOW_RAPID_CROSSBOW, CROSSBOW_SPELLBOUND_CROSSBOW -> Map.of();
            case CROSSBOW_BABY_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.GROWING);
            case CROSSBOW_BUTTERFLY_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.BONUS_SHOT);
            case CROSSBOW_CORRUPTED_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.DYNAMO);
            case CROSSBOW_DOOM_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.PUNCH, 1, Enchantments.POWER);
            case CROSSBOW_HARP_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(2, Enchantments.MULTISHOT);
            case CROSSBOW_HEAVY_CROSSBOW, CROSSBOW_HARPOON_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.PUNCH);
            case CROSSBOW_EXPLODING_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.FUSE_SHOT);
            case CROSSBOW_FERAL_SOUL_CROSSBOW, CROSSBOW_SOUL_CROSSBOW, CROSSBOW_SOUL_HUNTER_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.ENIGMA_RESONATOR);
            case CROSSBOW_FIREBOLT_THROWER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.FUSE_SHOT, EnchantmentsID.CHAIN_REACTION);
            case CROSSBOW_SCATTER_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.MULTISHOT);
            case CROSSBOW_IMPLODING_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.FUSE_SHOT, EnchantmentsID.GRAVITY);
            case CROSSBOW_VOIDCALLER_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.GRAVITY);
            case CROSSBOW_LIGHTNING_HARP_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.MULTISHOT, EnchantmentsID.RICOCHET, EnchantmentsID.THUNDERING);
            case CROSSBOW_NAUTICAL_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.PIERCING, Enchantments.PUNCH);
            case CROSSBOW_PRIDE_OF_THE_PIGLINS, CROSSBOW_THE_SLICER -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.PIERCING);
            case CROSSBOW_SHADOW_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.SHADOW_SHOT);
            case CROSSBOW_SLAYER_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, Enchantments.POWER, EnchantmentsID.RICOCHET);
            case CROSSBOW_VEILED_CROSSBOW -> CleanlinessHelper.mcdw$checkInnateEnchantmentEnabled(1, EnchantmentsID.SHADOW_SHOT, EnchantmentsID.SHADOW_BARB);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public McdwCrossbow makeWeapon() {
        McdwCrossbow mcdwCrossbow = new McdwCrossbow(this, CleanlinessHelper.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);
        getItemsEnum().put(this, mcdwCrossbow);
        return mcdwCrossbow;
    }
}