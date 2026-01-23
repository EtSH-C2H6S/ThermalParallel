package com.c2h6s.thermal_parallel;

import cofh.core.config.ConfigManager;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.NewRegistryEvent;
import org.slf4j.Logger;

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

}
