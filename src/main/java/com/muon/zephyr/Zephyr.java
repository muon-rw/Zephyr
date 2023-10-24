package com.muon.zephyr;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zephyr implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("zephyr");
	public static void structureDatapack() {
		ResourceLocation id = Zephyr.loc("zenith_replacer");
		ModContainer container = getModContainer(id);
		ResourceManagerHelper.registerBuiltinResourcePack(id, container,  ResourcePackActivationType.DEFAULT_ENABLED);
	}
	private static ModContainer getModContainer(ResourceLocation pack) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
				if (mod.findPath("resourcepacks/" + pack.getPath()).isPresent()) {
					LOGGER.info("LOADING DEV ENVIRONMENT DATAPACK");
					return mod;
				}
			}
		}
		return FabricLoader.getInstance().getModContainer(pack.getNamespace()).orElseThrow();
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Zenith Spell Power Compat...");
		LootCategories.init();
		structureDatapack();
	}
	public static ResourceLocation loc(String id) {
		return new ResourceLocation("zephyr", id);
	}
}