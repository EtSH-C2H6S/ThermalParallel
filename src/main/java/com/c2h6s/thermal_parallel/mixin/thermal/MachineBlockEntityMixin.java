package com.fg.tlt_tech.mixin.thermal;

import cofh.core.common.block.entity.BlockEntityCoFH;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import cofh.thermal.lib.util.recipes.MachineProperties;
import com.fg.tlt_tech.util.IMachinePropertiesMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MachineBlockEntity.class,remap = false)
public abstract class MachineBlockEntityMixin extends BlockEntityCoFH {
    public MachineBlockEntityMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Unique private boolean tlt_tech$delayInactive;

    @Shadow public abstract MachineProperties getMachineProperties();

    @Shadow protected abstract void resolveOutputs();

    @Shadow protected abstract void resolveInputs();

    @Shadow protected abstract boolean validateInputs();

    @Shadow protected abstract boolean validateOutputs();

    @Shadow protected abstract void processOff();
/*
    @Inject(method = "tickServer",at = @At("HEAD"))
    public void cancelDelay(CallbackInfo ci){
        if (tlt_tech$delayInactive&&(!validateInputs() || !validateOutputs())) processOff();
        tlt_tech$delayInactive = false;
    }

    @Redirect(method = "tickServer",at = @At(value = "INVOKE", target = "Lcofh/thermal/lib/common/block/entity/MachineBlockEntity;processOff()V",ordinal = 0))
    public void delayProcessOff(MachineBlockEntity instance){
        if (!tlt_tech$delayInactive) processOff();
    }

 */

    @Inject(method = "tickServer",at = @At(value = "INVOKE", target = "Lcofh/thermal/lib/common/block/entity/MachineBlockEntity;transferOutput()V",ordinal = 0))
    public void addParallelLogic(CallbackInfo ci){
        int parallel = (int) IMachinePropertiesMixin.getMaxParallel(getMachineProperties());
        if (parallel>0) {
            for (int i = 0; i < parallel; i++) {
                if (!validateInputs() || !validateOutputs()) {
                    return;
                }
                resolveOutputs();
                resolveInputs();
                markDirtyFast();
            }
        }
    }
}
