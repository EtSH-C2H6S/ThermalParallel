package com.c2h6s.thermal_parallel.init;

import cofh.core.util.helpers.AugmentDataHelper;
import cofh.thermal.lib.common.item.AugmentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cofh.lib.util.constants.NBTTags.*;
import static com.c2h6s.thermal_parallel.ThermalParallel.MODID;
import static com.c2h6s.thermal_parallel.util.TePaConstants.*;

public class TePaItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<AugmentItem> AUGMENT_PARALLEL_1 = ITEMS.register("argument_parallel",()->new AugmentItem(new Item.Properties(), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_MACHINE_PARALLEL,1).build()
    ));
    public static final RegistryObject<AugmentItem> AUGMENT_PARALLEL_2 = ITEMS.register("argument_parallel_2",()->new AugmentItem(new Item.Properties(), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_MACHINE_PARALLEL,4).build()
    ));
    public static final RegistryObject<AugmentItem> AUGMENT_PARALLEL_3 = ITEMS.register("argument_parallel_3",()->new AugmentItem(new Item.Properties(), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_MACHINE_PARALLEL,16).build()
    ));
}
