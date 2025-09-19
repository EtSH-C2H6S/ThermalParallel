package com.fg.tlt_tech.mixin.thermal;

import cofh.core.util.helpers.AugmentableHelper;
import cofh.lib.api.StorageGroup;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.lib.common.inventory.ManagedItemInv;
import cofh.lib.util.constants.NBTTags;
import cofh.thermal.lib.common.block.entity.AugmentableBlockEntity;
import com.fg.tlt_tech.util.Constants;
import com.fg.tlt_tech.util.IAugmentableBlockEntityMixin;
import com.fg.tlt_tech.util.IItemStorageCoFHMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Mixin(value = AugmentableBlockEntity.class,remap = false)
public abstract class AugmentableBlockEntityMixin implements IAugmentableBlockEntityMixin {
    @Shadow protected CompoundTag augmentNBT;

    @Shadow protected ManagedItemInv inventory;

    @Shadow protected List<ItemStorageCoFH> augments;

    @Shadow protected abstract Predicate<ItemStack> augValidator();

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
        AugmentableHelper.setAttributeFromAugmentAdd(augmentNBT,augmentData, Constants.TAG_MACHINE_PARALLEL);
    }

    @Inject(method = "finalizeAttributes",at = @At(value = "HEAD"))
    public void finalizeAttributes(Map<Enchantment, Integer> enchantmentMap, CallbackInfo ci){
        int parallel = (int) AugmentableHelper.getAttributeMod(augmentNBT,Constants.TAG_MACHINE_PARALLEL);
        tlt_tech$parallel = parallel;
        if (parallel>0) {
            inventory.getInputSlots().forEach(itemStorageCoFH -> ((IItemStorageCoFHMixin) itemStorageCoFH).tlt_tech$setParallel(parallel));
            inventory.getOutputSlots().forEach(itemStorageCoFH -> ((IItemStorageCoFHMixin) itemStorageCoFH).tlt_tech$setParallel(parallel));
        }
    }
}
