package com.c2h6s.thermal_parallel;

import cofh.core.common.config.ConfigManager;
import com.c2h6s.thermal_parallel.init.TePaItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.NewRegistryEvent;
import org.slf4j.Logger;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static com.c2h6s.thermal_parallel.init.TePaItems.ITEMS;

@Mod(ThermalParallel.MODID)
public class ThermalParallel {

    public static final String MODID = "thermal_parallel";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final ConfigManager CONFIG_MANAGER = new ConfigManager();

    public ThermalParallel() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        CONFIG_MANAGER.register(modEventBus)
                .addCommonConfig(new ThermalParallelConfig());
        modEventBus.addListener(this::registrySetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    private void registrySetup(final NewRegistryEvent event) {

        CONFIG_MANAGER.setupClient();
        CONFIG_MANAGER.setupServer();
        CONFIG_MANAGER.setupCommon();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ResourceKey.create(Registries.CREATIVE_MODE_TAB,new ResourceLocation(ID_THERMAL,ID_THERMAL + ".items"))){
            event.accept(TePaItems.AUGMENT_PARALLEL_1);
            if (ThermalParallelConfig.ADD_EXTRA_PARALLEL_AUGMENTS_TO_TAB.get()){
                event.accept(TePaItems.AUGMENT_PARALLEL_2);
                event.accept(TePaItems.AUGMENT_PARALLEL_3);
            }
        }
    }

}
