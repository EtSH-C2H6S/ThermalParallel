package com.c2h6s.thermal_parallel.mixin.thermal;

import cofh.thermal.lib.common.block.entity.AugmentableBlockEntity;
import cofh.thermal.lib.common.block.entity.Reconfigurable4WayBlockEntity;
import com.c2h6s.thermal_parallel.ThermalParallelConfig;
import com.c2h6s.thermal_parallel.util.TePaConstants;
import com.c2h6s.thermal_parallel.util.mixin.IAugmentableBlockEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Reconfigurable4WayBlockEntity.class,remap = false)
public abstract class Reconfigurable4WayBlockEntityMixin extends AugmentableBlockEntity {
    public Reconfigurable4WayBlockEntityMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }


    @Inject(at = @At("RETURN"),method = "getInputItemAmount",cancellable = true)
    public void addParallelInput(CallbackInfoReturnable<Integer> cir){
        if (TePaConstants.Config.PARALLEL_INCREASE_ITEM_TRANSFER) {
            int parallel = ((IAugmentableBlockEntityMixin) this).tlt_tech$getParallel();
            if (parallel > 0) cir.setReturnValue(cir.getReturnValueI() * (1 + parallel));
        }
    }
    @Inject(at = @At("RETURN"),method = "getOutputItemAmount",cancellable = true)
    public void addParallelOutput(CallbackInfoReturnable<Integer> cir){
        if (TePaConstants.Config.PARALLEL_INCREASE_ITEM_TRANSFER) {
            int parallel = ((IAugmentableBlockEntityMixin) this).tlt_tech$getParallel();
            if (parallel > 0) cir.setReturnValue(cir.getReturnValueI() * (1 + parallel));
        }
    }
    @Inject(at = @At("RETURN"),method = "getInputFluidAmount",cancellable = true)
    public void addParallelInputFluid(CallbackInfoReturnable<Integer> cir){
        if (TePaConstants.Config.PARALLEL_INCREASE_FLUID_TRANSFER) {
            int parallel = ((IAugmentableBlockEntityMixin) this).tlt_tech$getParallel();
            if (parallel > 0) cir.setReturnValue(cir.getReturnValueI() * (1 + parallel));
        }
    }
    @Inject(at = @At("RETURN"),method = "getOutputFluidAmount",cancellable = true)
    public void addParallelOutputFluid(CallbackInfoReturnable<Integer> cir){
        if (TePaConstants.Config.PARALLEL_INCREASE_FLUID_TRANSFER) {
            int parallel = ((IAugmentableBlockEntityMixin) this).tlt_tech$getParallel();
            if (parallel > 0) cir.setReturnValue(cir.getReturnValueI() * (1 + parallel));
        }
    }
}
