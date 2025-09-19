package com.fg.tlt_tech.mixin.thermal;

import cofh.thermal.lib.util.ThermalAugmentRules;
import com.fg.tlt_tech.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ThermalAugmentRules.class,remap = false)
public class ThermalAugmentRulesMixin {
    @Inject(at = @At("RETURN"),method = "isAdditive", cancellable = true)
    private static void setCustomAdditive(String mod, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValueZ()) cir.setReturnValue(Constants.CUSTOM_ADD.contains(mod));
    }
    @Inject(at = @At("RETURN"),method = "isInteger", cancellable = true)
    private static void setCustomInt(String mod, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValueZ()) cir.setReturnValue(Constants.CUSTOM_INT.contains(mod));
    }
}
