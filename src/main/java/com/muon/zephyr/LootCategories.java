package com.muon.zephyr;

import dev.shadowsoffire.apotheosis.adventure.AdventureConfig;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
// import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
// import net.spell_engine.api.item.weapon.SpellWeaponItem;
// import net.spell_engine.api.item.weapon.StaffItem;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.attributes.EntityAttributes_SpellPower;

import static dev.shadowsoffire.apotheosis.adventure.loot.LootCategory.HEAVY_WEAPON;
import static dev.shadowsoffire.apotheosis.adventure.loot.LootCategory.VALUES;

public class LootCategories {
    public static final LootCategory ELEMENTAL_STAFF = LootCategory.register(LootCategory.SWORD, "ELEMENTAL_STAFF", s->
            s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.ARCANE)).stream().anyMatch((m) -> m.getAmount() > 0.0) &&
            s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.FIRE)).stream().anyMatch((m) -> m.getAmount() > 0.0) &&
            s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.FROST)).stream().anyMatch((m) -> m.getAmount() > 0.0),
            arr(EquipmentSlot.MAINHAND));
    public static final LootCategory FIRE_STAFF = LootCategory.register(LootCategory.SWORD, "FIRE_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.FIRE)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    public static final LootCategory FROST_STAFF = LootCategory.register(LootCategory.SWORD, "FROST_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.FROST)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    public static final LootCategory ARCANE_STAFF = LootCategory.register(LootCategory.SWORD, "ARCANE_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.ARCANE)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    public static final LootCategory SOUL_STAFF = LootCategory.register(LootCategory.SWORD, "SOUL_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.SOUL)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    public static final LootCategory HEALING_STAFF = LootCategory.register(LootCategory.SWORD, "HEALING_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.HEALING)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    public static final LootCategory LIGHTNING_STAFF = LootCategory.register(LootCategory.SWORD, "LIGHTNING_STAFF", s-> s.getItem().getAttributeModifiers(s, EquipmentSlot.MAINHAND).get(EntityAttributes_SpellPower.POWER.get(MagicSchool.LIGHTNING)).stream().anyMatch((m) -> m.getAmount() > 0.0), arr(EquipmentSlot.MAINHAND));
    // We add to the HEAVY_WEAPON criteria with a Mixin instead.
    // public static final LootCategory TWO_HANDED = LootCategory.register(LootCategory.SWORD, "TWO_HANDED", itemStack -> { WeaponAttributes weaponAttributes = WeaponRegistry.getAttributes(itemStack); return weaponAttributes != null && weaponAttributes.isTwoHanded();}, arr(EquipmentSlot.MAINHAND))
    private static EquipmentSlot[] arr(EquipmentSlot... s) {
        return s;
    }
    public static boolean isElementalStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(ELEMENTAL_STAFF);}
    public static boolean isFireStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(FIRE_STAFF);}
    public static boolean isFrostStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(FROST_STAFF);}
    public static boolean isArcaneStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(ARCANE_STAFF);}
    public static boolean isSoulStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(SOUL_STAFF);}
    public static boolean isHealingStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(HEALING_STAFF);}
    public static boolean isLightningStaff(ItemStack stack) { return LootCategory.forItem(stack).equals(LIGHTNING_STAFF);}

    public static void init() {}

}
