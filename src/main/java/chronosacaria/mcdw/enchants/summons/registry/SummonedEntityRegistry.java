package chronosacaria.mcdw.enchants.summons.registry;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;

public class SummonedEntityRegistry {
    public static final Map<EntityType<? extends LivingEntity>, DefaultAttributeContainer> ATTRIBUTES =
            Maps.newHashMap();
    private static final List<EntityType<?>> SUMMONED_ENTITIES = Lists.newArrayList();

    public static final EntityType<SummonedBeeEntity> SUMMONED_BEE_ENTITY =
            FabricEntityTypeBuilder
                    .create(SpawnGroup.CREATURE, SummonedBeeEntity::new)
                    .dimensions(EntityDimensions.fixed(1,2))
                    .build();

    public static void register(){
        registerEntity("summoned_bee", SUMMONED_BEE_ENTITY);
    }

    public static void registerEntity(String name, EntityType<? extends LivingEntity> entity){
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Mcdw.MOD_ID, name), entity);
        ATTRIBUTES.put(entity, MobEntity.createMobAttributes().build());
    }

}
