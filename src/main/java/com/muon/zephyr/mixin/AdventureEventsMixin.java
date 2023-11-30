package com.muon.zephyr.mixin;

import dev.shadowsoffire.apotheosis.adventure.AdventureEvents;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixHelper;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.attributeslib.api.ItemAttributeModifierEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdventureEvents.class)
public class AdventureEventsMixin {
    @Inject(method = "affixModifiers", at = @At("HEAD"), cancellable = true, remap = false)
    private static void modifyAffixModifiers(CallbackInfo ci) {
        ItemAttributeModifierEvent.GATHER_TOOLTIPS.register(e -> {
            ItemStack stack = e.stack;
            if (stack.hasTag()) {
                var affixes = AffixHelper.getAffixes(stack);
                affixes.forEach((afx, inst) -> inst.addModifiers(e.slot, e::addModifier));
                if (!affixes.isEmpty() && LootCategory.forItem(stack) == LootCategory.HEAVY_WEAPON && e.slot == EquipmentSlot.MAINHAND) {
                    // Skipping the part that modifies attack speed for heavy weapons
                    /*
                    double amt = -0.15 - 0.10 * affixes.values().stream().findAny().get().rarity().get().ordinal();
                    AttributeModifier baseAS = e.originalModifiers.get(Attributes.ATTACK_SPEED).stream().filter(a -> ItemAccess.getBaseAS() == a.getId()).findFirst().orElse(null);
                    if (baseAS != null) {
                        double value = 4 + baseAS.getAmount();
                        double clampedAmt = 0.4F / value - 1;
                        amt = Math.max(amt, clampedAmt);
                        if (amt >= 0) return;
                    }
                    e.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(HEAVY_WEAPON_AS, "Heavy Weapon AS", amt, Operation.MULTIPLY_TOTAL));
                    */
                }
            }
        });
        // Don't cancel the original method execution
        ci.cancel();
    }
}
