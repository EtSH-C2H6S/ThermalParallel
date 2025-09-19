package com.fg.tlt_tech.mixin.thermal;

import cofh.lib.common.inventory.ItemStorageCoFH;
import com.fg.tlt_tech.util.IItemStorageCoFHMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStorageCoFH.class,remap = false)
public class ItemStorageCoFHMixin implements IItemStorageCoFHMixin {
    @Unique private int tlt_tech$parallel;

    @Override
    public void tlt_tech$setParallel(int count) {
        tlt_tech$parallel = count;
    }

    @Override
    public int tlt_tech$getParallel() {
        return tlt_tech$parallel;
    }

    @Inject(at = @At("RETURN"),method = "getSlotLimit",cancellable = true)
    public void modifyItemStorage(int slot, CallbackInfoReturnable<Integer> cir){
        if (tlt_tech$parallel>0){
            cir.setReturnValue(cir.getReturnValueI()*(tlt_tech$parallel+1));
        }
    }
}
