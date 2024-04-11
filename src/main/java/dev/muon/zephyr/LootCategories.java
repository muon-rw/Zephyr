package dev.muon.zephyr;

import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.spell_engine.api.item.weapon.SpellWeaponItem;
import net.spell_engine.api.item.weapon.StaffItem;


public class LootCategories {
    public static final LootCategory STAFF = LootCategory.register(LootCategory.SWORD, "staff",
            s -> s.getItem() instanceof StaffItem,
            arr(EquipmentSlot.MAINHAND));
    public static final LootCategory SPELL_WEAPON = LootCategory.register(LootCategory.SWORD, "spell_weapon",
            s -> s.getItem() instanceof SpellWeaponItem,
            arr(EquipmentSlot.MAINHAND));

    private static EquipmentSlot[] arr(EquipmentSlot... s) {
        return s;
    }

    public static boolean isSpellWeapon(ItemStack stack) {
        return LootCategory.forItem(stack).equals(SPELL_WEAPON);
    }

    public static boolean isStaff(ItemStack stack) {
        return LootCategory.forItem(stack).equals(STAFF);
    }

    public static void init() {}
}
