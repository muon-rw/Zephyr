package dev.muon.zephyr.mixin;

import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(LootCategory.class)
public class LootCategoryMixin {

    /**
     * Redirecting Heavy Weapon Registration instead of modifying return values
     * Reasons:
     * ShieldBreakerTest is not performant
     * Presents mod compatibility issues (currently a stack overflow with ExtraRPGAttributes)
     * Separating solely by 1h vs. 2h is more intuitive.
     * When Apotheosis drops the Heavy Weapon category, this is how we'll differentiate anyway.
     */
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/shadowsoffire/apotheosis/adventure/loot/LootCategory;register(Ljava/lang/String;Ljava/util/function/Predicate;[Lnet/minecraft/world/entity/EquipmentSlot;)Ldev/shadowsoffire/apotheosis/adventure/loot/LootCategory;",
                    ordinal = 4
            )
    )
    private static LootCategory redirectHeavyWeaponRegister(String name, Predicate<ItemStack> validator, EquipmentSlot[] slots) {
        Predicate<ItemStack> newValidator = itemStack -> {
            WeaponAttributes weaponAttributes = WeaponRegistry.getAttributes(itemStack);
            return weaponAttributes != null && weaponAttributes.isTwoHanded();
        };
        return LootCategory.register(null, name, newValidator, slots);
    }
}