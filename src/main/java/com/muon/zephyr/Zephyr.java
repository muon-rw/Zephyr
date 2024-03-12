package com.muon.zephyr;

import com.muon.zephyr.affixes.MagicTelepathicAffix;
import dev.shadowsoffire.apotheosis.Apotheosis;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixRegistry;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Zephyr implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("zephyr");
	public static void overrides() {
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
	public static final class Affixes {
		public static final DynamicHolder<MagicTelepathicAffix> TELEPATHIC;
		public Affixes() {
		}
		static {
			TELEPATHIC = AffixRegistry.INSTANCE.holder(Zephyr.loc("telepathic"));
		}
	}
	@Override
	public void onInitialize() {
		LOGGER.info("Loading Zephyr");
		LootCategories.init();
		overrides();
	}
	public static ResourceLocation loc(String id) {
		return new ResourceLocation("zephyr", id);
	}
}