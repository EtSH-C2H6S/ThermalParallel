package com.c2h6s.thermal_parallel;

import cofh.core.common.config.IBaseConfig;
import com.c2h6s.thermal_parallel.util.TePaConstants;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.lib.util.Constants.FALSE;
import static cofh.lib.util.Constants.TRUE;

public class ThermalParallelConfig implements IBaseConfig {
    @Override
    public void apply(ForgeConfigSpec.Builder builder) {
        builder.push("Balance").comment("平衡性");
        this.PARALLEL_INCREASE_ENERGY_CONSUMPTION = builder
                .comment("If true, energy consumption will be multiplied by parallel value. Default true.")
                .comment("如果设置为true，则机器的能耗会随并行增多而成倍增加。默认：true。")
                .comment("The energy consumed by parallel tasks will be consumed by the end of a process.")
                .comment("并行的配方处理带来的额外能量需求会在处理过程结束的瞬间消耗（遗憾的是目前没有找到一个更好的方法来处理并行能耗的问题）。")
                .define("Parallel Increase Energy Consumption",true);
        this.BASE_MOD_AFFECT_PARALLEL = builder
                .comment("If true, parallel value will be multiplied by Scale Factor. Default false.")
                .comment("如果设置为true，则机器的并行数量会被整合组件带来的基础倍率增幅。默认：false。")
                .comment("If you want to make parallel augment op, set this to true.")
                .comment("如果你不在乎平衡性可以把这个设置成true然后加上别的热力附属，享受极致的速率。")
                .define("Base Mod Affect Parallel",false);
        ADD_EXTRA_PARALLEL_AUGMENTS_TO_TAB = builder
                .comment("If true, higher tiers of parallel upgrades will be added to creative tab. Default false.")
                .comment("如果设置为true，则更高等级的并行插件会被加进创造模式物品栏。默认：false。")
                .comment("Note that those parallel upgrades don't have a recipe and is relatively op, remain this false once you are not a modpack developer.")
                .comment("注：更高级的并行插件没有默认配方并且会略微超模，如果你不是整合包作者请不要把此项打开。")
                .define("Add Extra Parallel Augments To Tab",false);
        builder.pop();
        builder.push("Storage Modify").comment("机器存储修改");
        this.PARALLEL_INCREASE_ITEM_CAPACITY = builder
                .comment("If true, parallel upgrades will increase item input/output storage capacity for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器的输入/输出物品存储上限。默认：true。")
                .define("Parallel Increase Item Cap",true);
        this.PARALLEL_INCREASE_FLUID_CAPACITY = builder
                .comment("If true, parallel upgrades will increase fluid storage capacity for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器的流体存储上限。默认：true。")
                .define("Parallel Increase Fluid Cap",true);
        this.PARALLEL_INCREASE_ENERGY_CAPACITY = builder
                .comment("If true, parallel upgrades will increase energy storage capacity for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器的能量存储上限。默认：true。")
                .define("Parallel Increase Energy Cap",true);
        this.PARALLEL_INCREASE_ITEM_TRANSFER = builder
                .comment("If true, parallel upgrades will increase max item auto input/output speed for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器进行自动提取/弹出物品的速率上限。默认：true。")
                .define("Parallel Increase Item Transfer",true);
        this.PARALLEL_INCREASE_FLUID_TRANSFER = builder
                .comment("If true, parallel upgrades will increase max fluid auto input/output speed for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器进行自动提取/弹出流体的速率上限。默认：true。")
                .define("Parallel Increase Fluid Transfer",true);
        this.PARALLEL_INCREASE_ENERGY_TRANSFER = builder
                .comment("If true, parallel upgrades will increase energy transfer for a machine. Default true.")
                .comment("如果设置为true，则并行升级会同步提高机器的能量传输上限。默认：true。")
                .define("Parallel Increase Energy Transfer",true);
        builder.pop();
    }

    @Override
    public void refresh() {
        if (this.PARALLEL_INCREASE_ITEM_CAPACITY!=null){
            TePaConstants.Config.PARALLEL_INCREASE_ITEM_CAPACITY = this.PARALLEL_INCREASE_ITEM_CAPACITY.get();
        }
        if (this.PARALLEL_INCREASE_FLUID_CAPACITY!=null){
            TePaConstants.Config.PARALLEL_INCREASE_FLUID_CAPACITY = this.PARALLEL_INCREASE_FLUID_CAPACITY.get();
        }
        if (this.PARALLEL_INCREASE_ENERGY_CAPACITY!=null){
            TePaConstants.Config.PARALLEL_INCREASE_ENERGY_CAPACITY = this.PARALLEL_INCREASE_ENERGY_CAPACITY.get();
        }
        if (this.PARALLEL_INCREASE_ITEM_TRANSFER!=null){
            TePaConstants.Config.PARALLEL_INCREASE_ITEM_TRANSFER = this.PARALLEL_INCREASE_ITEM_TRANSFER.get();
        }
        if (this.PARALLEL_INCREASE_FLUID_TRANSFER!=null){
            TePaConstants.Config.PARALLEL_INCREASE_FLUID_TRANSFER = this.PARALLEL_INCREASE_FLUID_TRANSFER.get();
        }
        if (this.PARALLEL_INCREASE_ENERGY_TRANSFER!=null){
            TePaConstants.Config.PARALLEL_INCREASE_ENERGY_TRANSFER = this.PARALLEL_INCREASE_ENERGY_TRANSFER.get();
        }
        if (this.BASE_MOD_AFFECT_PARALLEL!=null){
            TePaConstants.Config.BASE_MOD_AFFECT_PARALLEL = this.BASE_MOD_AFFECT_PARALLEL.get();
        }
        if (this.PARALLEL_INCREASE_ENERGY_CONSUMPTION!=null){
            TePaConstants.Config.PARALLEL_INCREASE_ENERGY_CONSUMPTION = this.PARALLEL_INCREASE_ENERGY_CONSUMPTION.get();
        }
    }

    public Supplier<Boolean> PARALLEL_INCREASE_ITEM_CAPACITY ;
    public Supplier<Boolean> PARALLEL_INCREASE_FLUID_CAPACITY ;
    public Supplier<Boolean> PARALLEL_INCREASE_ENERGY_CAPACITY ;
    public Supplier<Boolean> PARALLEL_INCREASE_ITEM_TRANSFER ;
    public Supplier<Boolean> PARALLEL_INCREASE_FLUID_TRANSFER ;
    public Supplier<Boolean> PARALLEL_INCREASE_ENERGY_TRANSFER ;
    public static Supplier<Boolean> ADD_EXTRA_PARALLEL_AUGMENTS_TO_TAB = FALSE;
    public Supplier<Boolean> BASE_MOD_AFFECT_PARALLEL ;
    public Supplier<Boolean> PARALLEL_INCREASE_ENERGY_CONSUMPTION ;

}
