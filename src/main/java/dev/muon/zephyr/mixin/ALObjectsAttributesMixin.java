package dev.muon.zephyr.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = dev.shadowsoffire.attributeslib.api.ALObjects.Attributes.class, remap = false)
public class ALObjectsAttributesMixin {
    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/ResourceLocation;Ljava/lang/Object;)Ljava/lang/Object;"), remap = true)
    private static <T> T redirectRegister(Registry<T> registry, ResourceLocation name, T object) {
        if (registry == BuiltInRegistries.ATTRIBUTE && !registry.containsKey(name)) {
            return Registry.register(registry, name, object);
        }
        return null;
    }
}