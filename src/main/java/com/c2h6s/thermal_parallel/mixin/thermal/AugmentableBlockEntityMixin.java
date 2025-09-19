package com.c2h6s.thermal_parallel.mixin.thermal;

import cofh.core.util.helpers.AugmentableHelper;
import cofh.lib.common.energy.EnergyStorageCoFH;
import cofh.lib.common.fluid.ManagedTankInv;
import cofh.lib.common.inventory.ManagedItemInv;
import cofh.thermal.lib.common.block.entity.AugmentableBlockEntity;
import com.c2h6s.thermal_parallel.ThermalParallelConfig;
import com.c2h6s.thermal_parallel.util.TePaConstants;
import com.c2h6s.thermal_parallel.util.mixin.IAugmentableBlockEntityMixin;
import com.c2h6s.thermal_parallel.util.mixin.IItemStorageCoFHMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(value = AugmentableBlockEntity.class,remap = false)
public abstract class AugmentableBlockEntityMixin implements IAugmentableBlockEntityMixin {
    @Shadow protected CompoundTag augmentNBT;

    @Shadow protected ManagedItemInv inventory;

    @Shadow protected ManagedTankInv tankInv;
    @Shadow protected EnergyStorageCoFH energyStorage;
    @Unique private int tlt_tech$parallel;

    @Override
    public int tlt_tech$getParallel() {
        return tlt_tech$parallel;
    }

    @Override
    public void tlt_tech$setParallel(int parallel) {
        tlt_tech$parallel = parallel;
    }

    @Inject(method = "setAttributesFromAugment",at = @At(value = "INVOKE", target = "Lcofh/core/util/helpers/AugmentableHelper;setAttributeFromAugmentMax(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/CompoundTag;Ljava/lang/String;)V",ordinal = 0,shift = At.Shift.AFTER))
    public void setParallelAttribute(CompoundTag augmentData, CallbackInfo ci){
        AugmentableHelper.setAttributeFromAugmentAdd(augmentNBT,augmentData, TePaConstants.TAG_MACHINE_PARALLEL);
    }

    @Inject(method = "finalizeAttributes",at = @At(value = "TAIL"),locals = LocalCapture.CAPTURE_FAILHARD)
    public void finalizeAttributes(Map<Enchantment, Integer> enchantmentMap, CallbackInfo ci, float holdingMod, float baseMod, float energyStorageMod, float fluidStorageMod, float itemStorageMod, float xpStorageMod, float energyXferMod, int storedXp, CompoundTag filterNBT){
        int parallel = (int) AugmentableHelper.getAttributeMod(augmentNBT,TePaConstants.TAG_MACHINE_PARALLEL);
        if (TePaConstants.Config.BASE_MOD_AFFECT_PARALLEL){
            parallel = (int) (((parallel+1)*baseMod) - 1);
        }
        tlt_tech$parallel = parallel;
        final int finalParallel = parallel;
        if (finalParallel>0) {
            if (TePaConstants.Config.PARALLEL_INCREASE_ITEM_CAPACITY) {
                inventory.getInputSlots().forEach(itemStorageCoFH -> ((IItemStorageCoFHMixin) itemStorageCoFH).tlt_tech$setParallel(finalParallel+1));
                inventory.getOutputSlots().forEach(itemStorageCoFH -> ((IItemStorageCoFHMixin) itemStorageCoFH).tlt_tech$setParallel(finalParallel+1));
            }
            if (TePaConstants.Config.PARALLEL_INCREASE_FLUID_CAPACITY){
                for (int i = 0; i < tankInv.getTanks(); ++i) {
                    var tank = tankInv.getTank(i);
                    tankInv.getTank(i).setCapacity((finalParallel+1)*tank.getCapacity());
                }
            }
            if (TePaConstants.Config.PARALLEL_INCREASE_ENERGY_CAPACITY){
                energyStorage.setCapacity(energyStorage.getCapacity()*(finalParallel+1));
            }
            if (TePaConstants.Config.PARALLEL_INCREASE_ENERGY_TRANSFER){
                energyStorage.setMaxReceive(energyStorage.getMaxReceive()*(finalParallel+1));
                energyStorage.setMaxExtract(energyStorage.getMaxExtract()*(finalParallel+1));
            }
        }
    }
}
