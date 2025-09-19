package com.c2h6s.thermal_parallel.util;

import java.util.function.Supplier;

public class TePaConstants {
    public static final String TAG_MACHINE_PARALLEL = "MachineParallel";

    public static class Config{
        public static boolean PARALLEL_INCREASE_ITEM_CAPACITY = true ;
        public static boolean PARALLEL_INCREASE_FLUID_CAPACITY = true;
        public static boolean PARALLEL_INCREASE_ENERGY_CAPACITY = true;
        public static boolean PARALLEL_INCREASE_ITEM_TRANSFER = true;
        public static boolean PARALLEL_INCREASE_FLUID_TRANSFER = true;
        public static boolean PARALLEL_INCREASE_ENERGY_TRANSFER = true;
        public static boolean PARALLEL_INCREASE_ENERGY_CONSUMPTION = true;
        public static boolean BASE_MOD_AFFECT_PARALLEL = false;
    }

}
