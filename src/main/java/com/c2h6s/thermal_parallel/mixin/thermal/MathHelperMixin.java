package com.c2h6s.thermal_parallel.mixin.thermal;

import cofh.lib.util.helpers.MathHelper;
import com.mojang.logging.LogUtils;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(value = MathHelper.class)
public class MathHelperMixin {
    @Redirect(method = "<clinit>",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;create()Lnet/minecraft/util/RandomSource;"))
    private static RandomSource replaceRandomSource(){
        LogUtils.getLogger().info("Replaced Thermal's RandomSource to thread safe.");
        return RandomSource.createThreadSafe();
    }
}