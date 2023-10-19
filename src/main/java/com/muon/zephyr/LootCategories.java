package com.muon.zephyr;

import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.spell_engine.api.item.weapon.SpellWeaponItem;
import net.spell_engine.api.item.weapon.StaffItem;

public class LootCategories {
    public static final LootCategory STAFF = LootCategory.register(LootCategory.SWORD, "STAFF", s-> s.getItem() instanceof StaffItem, arr(EquipmentSlot.MAINHAND));
    public static final LootCategory SPELLWEAPON = LootCategory.register(LootCategory.SWORD, "SPELLWEAPON", s-> s.getItem() instanceof SpellWeaponItem, arr(EquipmentSlot.MAINHAND));
    private static EquipmentSlot[] arr(EquipmentSlot... s) {
        return s;
    }
    public static boolean isStaffItem(ItemStack stack) { return LootCategory.forItem(stack).equals(STAFF);}
    public static boolean isSpellWeapon(ItemStack stack) { return LootCategory.forItem(stack).equals(SPELLWEAPON);}
    public static void init() {}

}
