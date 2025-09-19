package com.c2h6s.thermal_parallel.mixin.thermal;

import cofh.thermal.lib.util.ThermalAugmentRules;
import com.c2h6s.thermal_parallel.util.TePaConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ThermalAugmentRules.class,remap = false)
public class ThermalAugmentRulesMixin {
    @Inject(at = @At("RETURN"),method = "isAdditive", cancellable = true)
    private static void setCustomAdditive(String mod, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValueZ()) cir.setReturnValue(TePaConstants.TAG_MACHINE_PARALLEL.equals(mod));
    }
    @Inject(at = @At("RETURN"),method = "isInteger", cancellable = true)
    private static void setCustomInt(String mod, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValueZ()) cir.setReturnValue(TePaConstants.TAG_MACHINE_PARALLEL.equals(mod));
    }
}
