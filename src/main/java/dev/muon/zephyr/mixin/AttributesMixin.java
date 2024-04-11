package dev.muon.zephyr.mixin;

import dev.shadowsoffire.attributeslib.AttributesLib;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.world.entity.ai.attributes.Attributes.class, remap = false)
public abstract class AttributesMixin {
    private static void register(String name, Attribute attribute) {
        ResourceLocation resourceLocation = AttributesLib.loc(name);
        if (!BuiltInRegistries.ATTRIBUTE.containsKey(resourceLocation)) {
            Registry.register(BuiltInRegistries.ATTRIBUTE, resourceLocation, attribute);
        }
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void setup(CallbackInfo info) {
        register("draw_speed", ALObjects.Attributes.DRAW_SPEED);
        register("crit_chance", ALObjects.Attributes.CRIT_CHANCE);
        register("cold_damage", ALObjects.Attributes.COLD_DAMAGE);
        register("fire_damage", ALObjects.Attributes.FIRE_DAMAGE);
        register("life_steal", ALObjects.Attributes.LIFE_STEAL);
        register("current_hp_damage", ALObjects.Attributes.CURRENT_HP_DAMAGE);
        register("overheal", ALObjects.Attributes.OVERHEAL);
        register("ghost_health", ALObjects.Attributes.GHOST_HEALTH);
        register("mining_speed", ALObjects.Attributes.MINING_SPEED);
        register("arrow_velocity", ALObjects.Attributes.ARROW_VELOCITY);
        register("healing_received", ALObjects.Attributes.HEALING_RECEIVED);
        register("armor_pierce", ALObjects.Attributes.ARMOR_PIERCE);
        register("armor_shred", ALObjects.Attributes.ARMOR_SHRED);
        register("prot_pierce", ALObjects.Attributes.PROT_PIERCE);
        register("prot_shred", ALObjects.Attributes.PROT_SHRED);
        register("dodge_chance", ALObjects.Attributes.DODGE_CHANCE);
        register("elytra_flight", ALObjects.Attributes.ELYTRA_FLIGHT);
        register("creative_flight", ALObjects.Attributes.CREATIVE_FLIGHT);
    }
}