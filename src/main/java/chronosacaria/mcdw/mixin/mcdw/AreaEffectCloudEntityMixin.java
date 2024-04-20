/*
Timefall Development License 1.2
Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.api.interfaces.IExclusiveAOECloud;
import chronosacaria.mcdw.api.util.AbilityHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(AreaEffectCloudEntity.class)
public class AreaEffectCloudEntityMixin implements IExclusiveAOECloud {

    @Shadow @Nullable private LivingEntity owner;
    @Unique
    private List<Boolean> exclusions = List.of(false, false, false);
    @Unique
    private LivingEntity livingEntity;

    @Unique
    private void setLivingEntity(LivingEntity livingEntity) { this.livingEntity = livingEntity; }

    @Unique
    private LivingEntity getLivingEntity() { return livingEntity; }

    @Override
    public List<Boolean> mcdw$getExclusions() {
        return exclusions;
    }

    @Override
    public void mcdw$setExclusions(boolean owner, boolean allies, boolean enemy) {
        exclusions = List.of(owner, allies, enemy);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void mcdw$nbtToTag(NbtCompound tag, CallbackInfo ci) {
        tag.putBoolean("owner", exclusions.get(0));
        tag.putBoolean("allies", exclusions.get(1));
        tag.putBoolean("enemy", exclusions.get(2));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void mcdw$nbtFromTag(NbtCompound tag, CallbackInfo ci) {
        this.exclusions = List.of(tag.getBoolean("owner"), tag.getBoolean("allies"), tag.getBoolean("enemy"));
    }

    @ModifyArgs(method = "tick", at = @At(value = "INVOKE",
            target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    public void mcdw$livingEntityGetter(Args args) {

        LivingEntity livingEntity = args.get(0);
        setLivingEntity(livingEntity);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"), cancellable = true)
    public void mcdw$pickyAOEClouds(CallbackInfo ci) {

        LivingEntity h = getLivingEntity();
        if (h == owner) {
            if (this.exclusions.get(0)) {
                ci.cancel();
            }
        } else if (this.owner != null && AbilityHelper.isTrueAlly(this.owner, h)) {
            if (this.exclusions.get(1)) {
                ci.cancel();
            }
        } else if (this.owner != null){ // h is an enemy
            if (this.exclusions.get(2)) {
                ci.cancel();
            }
        }
    }
}