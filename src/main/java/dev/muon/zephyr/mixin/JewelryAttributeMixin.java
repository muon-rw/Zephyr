package dev.muon.zephyr.mixin;

import dev.shadowsoffire.attributeslib.AttributesLib;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.fabricmc.loader.api.FabricLoader;
import net.jewelry.api.AttributeResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static net.jewelry.api.AttributeResolver.register;

@Mixin(AttributeResolver.class)
public abstract class JewelryAttributeMixin {
    @Inject(method = "setup", at = @At("TAIL"), remap = false)
    private static void setup(CallbackInfo Info) {
        if (FabricLoader.getInstance().isModLoaded("zenith_attributes") || FabricLoader.getInstance().isModLoaded("attributeslib")) {
            register (AttributesLib.loc("armor_pierce"), ALObjects.Attributes.ARMOR_PIERCE);
            register (AttributesLib.loc("crit_chance"), ALObjects.Attributes.CRIT_CHANCE);
            register (AttributesLib.loc("cold_damage"), ALObjects.Attributes.COLD_DAMAGE);
            register (AttributesLib.loc("fire_damage"), ALObjects.Attributes.FIRE_DAMAGE);
            register (AttributesLib.loc("life_steal"), ALObjects.Attributes.LIFE_STEAL);
            register (AttributesLib.loc("current_hp_damage"), ALObjects.Attributes.CURRENT_HP_DAMAGE);
            register (AttributesLib.loc("overheal"), ALObjects.Attributes.OVERHEAL);
            register (AttributesLib.loc("arrow_velocity"), ALObjects.Attributes.ARROW_VELOCITY);
            register (AttributesLib.loc("healing_received"), ALObjects.Attributes.HEALING_RECEIVED);
            register (AttributesLib.loc("armor_shred"), ALObjects.Attributes.ARMOR_SHRED);
            register (AttributesLib.loc("draw_speed"), ALObjects.Attributes.DRAW_SPEED);
            register (AttributesLib.loc("prot_pierce"), ALObjects.Attributes.PROT_PIERCE);
            register (AttributesLib.loc("prot_shred"), ALObjects.Attributes.PROT_SHRED);
            register (AttributesLib.loc("dodge_chance"), ALObjects.Attributes.DODGE_CHANCE);
            register (AttributesLib.loc("elytra_flight"), ALObjects.Attributes.ELYTRA_FLIGHT);
            register (AttributesLib.loc("creative_flight"), ALObjects.Attributes.CREATIVE_FLIGHT);

        }
    }
}