/*
Timefall Development License 1.2
Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(targets = "net/minecraft/screen/GrindstoneScreenHandler$4")
public class GetExperienceMixin {
    @Inject(method = "getExperience(Lnet/minecraft/item/ItemStack;)I", at = @At(value = "RETURN"), cancellable = true)
    private void mcdw$getExperience(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof IInnateEnchantment innateEnchantedWeapon) {
            int i = cir.getReturnValue();
            Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
            Map<Enchantment, Integer> innateMap = innateEnchantedWeapon.getInnateEnchantments();
            if (innateMap == null) return;
            for (Map.Entry<Enchantment, Integer> entry : innateMap.entrySet()) {
                Enchantment enchantment = entry.getKey();
                Integer integer = entry.getValue();
                if (map.containsKey(enchantment) && map.get(enchantment) >= integer) {
                    i -= enchantment.getMinPower(integer);
                }
            }
            cir.setReturnValue(i);
        }
    }
}
