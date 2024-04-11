package dev.muon.zephyr.mixin;

import dev.shadowsoffire.attributeslib.AttributesLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(dev.shadowsoffire.attributeslib.api.ALObjects.Attributes.class)
public class ALObjectsAttributesMixin {
    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/ResourceLocation;Ljava/lang/Object;)Ljava/lang/Object;"), remap = false)
    private static <T> T redirectRegister(Registry<T> registry, ResourceLocation name, T object) {
        if (registry == BuiltInRegistries.ATTRIBUTE && !registry.containsKey(name)) {
            return Registry.register(registry, name, object);
        }
        return null;
    }
}