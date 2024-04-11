package dev.muon.zephyr;

import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.spell_engine.api.item.weapon.StaffItem;


public class LootCategories {
    // Elemental Affixes are sorted automatically for any valid weapon
    // See AffixSchoolMapper
    public static final LootCategory STAFF = LootCategory.register(LootCategory.SWORD, "staff",
            s -> s.getItem() instanceof StaffItem && !isOneHanded(s),
            arr(EquipmentSlot.MAINHAND));
    public static final LootCategory WAND = LootCategory.register(LootCategory.SWORD, "wand",
            s -> s.getItem() instanceof StaffItem && isOneHanded(s),
            arr(EquipmentSlot.MAINHAND));
    private static boolean isOneHanded(ItemStack itemStack) {
        if (FabricLoader.getInstance().isModLoaded("bettercombat")) {
            WeaponAttributes weaponAttributes = WeaponRegistry.getAttributes(itemStack);
            return weaponAttributes != null && !weaponAttributes.isTwoHanded();
        }
        return false;
    }
    private static EquipmentSlot[] arr(EquipmentSlot... s) {
        return s;
    }

    public static boolean isWand(ItemStack stack) {
        return LootCategory.forItem(stack).equals(WAND);
    }
    public static boolean isStaff(ItemStack stack) {
        return LootCategory.forItem(stack).equals(STAFF);
    }

    public static void init() {}
}
