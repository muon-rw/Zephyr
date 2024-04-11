package dev.muon.zephyr;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import java.util.EnumSet;


public class Zephyr implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("zephyr");
	public static void overrides() {
		String packId = FabricLoader.getInstance().isModLoaded("apotheosis") ? "zephyr_apoth" : "zephyr_zenith";
		ResourceLocation id = Zephyr.loc(packId);
		ModContainer container = getModContainer(id);
		ResourceManagerHelper.registerBuiltinResourcePack(id, container, ResourcePackActivationType.DEFAULT_ENABLED);
	}
	private static ModContainer getModContainer(ResourceLocation pack) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
				if (mod.findPath("resourcepacks/" + pack.getPath()).isPresent()) {
					LOGGER.info("Loading Zephyr Data Files");
					return mod;
				}
			}
		}
		return FabricLoader.getInstance().getModContainer(pack.getNamespace()).orElseThrow();
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