/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

/*
public class SummonedEntityRegistry {
    public static final Map<EntityType<? extends LivingEntity>, DefaultAttributeContainer> ATTRIBUTES =
            Maps.newHashMap();

    public static final EntityType<SummonedBeeEntity> SUMMONED_BEE_ENTITY =
            registerEntity("summoned_bee_entity", EntityType.Builder
                    .create(SummonedBeeEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.4F, 0.4F)
                    .maxTrackingRange(10));

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> entityBuilder) {
        return Registry.register(Registries.ENTITY_TYPE, Mcdw.ID(name), entityBuilder.build());
    }
    public static void register(){
        registerEntity("summoned_bee", SUMMONED_BEE_ENTITY, SummonedBeeEntity.createSummonedBeeEntityAttributes());
    }

    public static void registerEntity(String name, EntityType<? extends LivingEntity> entity, DefaultAttributeContainer.Builder attributes){
        Registry.register(Registries.ENTITY_TYPE, Mcdw.ID(name), entity);
        ATTRIBUTES.put(entity, attributes.build());
    }

}

 */
