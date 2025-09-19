package com.c2h6s.thermal_parallel.mixin.thermal;

import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import cofh.thermal.lib.common.block.entity.Reconfigurable4WayBlockEntity;
import cofh.thermal.lib.util.recipes.MachineProperties;
import com.c2h6s.thermal_parallel.util.TePaConstants;
import com.c2h6s.thermal_parallel.util.mixin.IAugmentableBlockEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MachineBlockEntity.class,remap = false)
public abstract class MachineBlockEntityMixin extends Reconfigurable4WayBlockEntity {
    public MachineBlockEntityMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Shadow public abstract MachineProperties getMachineProperties();

    @Shadow protected abstract void resolveOutputs();

    @Shadow protected abstract void resolveInputs();

    @Shadow protected abstract boolean validateInputs();

    @Shadow protected abstract boolean validateOutputs();

    @Shadow protected int processMax;

    @Shadow protected int process;

    @Inject(method = "tickServer",at = @At(value = "INVOKE", target = "Lcofh/thermal/lib/common/block/entity/MachineBlockEntity;transferOutput()V",ordinal = 0))
    public void addParallelLogic(CallbackInfo ci){
        int parallel = ((IAugmentableBlockEntityMixin)this).tlt_tech$getParallel();
        if (parallel>0) {
            for (int i = 0; i < parallel; i++) {
                if (!validateInputs() || !validateOutputs()) {
                    return;
                }
                if (TePaConstants.Config.PARALLEL_INCREASE_ENERGY_CONSUMPTION){
                    if (energyStorage.getEnergyStored() < processMax) return;
                    energyStorage.modify(-processMax);
                }
                resolveOutputs();
                resolveInputs();
                markDirtyFast();
            }
        }
    }
}
