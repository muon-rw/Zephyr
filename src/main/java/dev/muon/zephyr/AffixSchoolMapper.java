package dev.muon.zephyr;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
import net.spell_power.api.enchantment.SpellPowerEnchanting;

import java.util.Set;

public class AffixSchoolMapper {
    /*

    Valid elemental affixes still need to be assigned the standard way for each desired loot category.
     They are then filtered to only apply to the appropriate matching gear sets based on the presence of attributes

     If you want to use this behavior, place your elemental affix in an /affixes/elemental/school_schoolname/ directory
     "schoolname" should match the entry name for a SpellSchool.
     ex.: /data/zephyr/affixes/elemental/school_fire/attribute.json matches SpellSchools.FIRE

     Currently, this sorting behavior only applies to weapons, primarily for balance.

    */
    public static Set<SpellSchool> getSpellSchoolsFromGear(ItemStack stack, EquipmentSlot slot) {
        return SpellPowerEnchanting.relevantSchools(stack, slot);
    }
    public static Set<SpellSchool> getSpellSchoolsFromWeapon(ItemStack stack) {
        return getSpellSchoolsFromGear(stack, EquipmentSlot.MAINHAND);
    }

    public static SpellSchool getSpellSchoolForAffix(String affixId) {
        ResourceLocation affixResource = new ResourceLocation(affixId);
        String path = affixResource.getPath();

        if (path.contains("elemental/")) {
            String[] pathParts = path.split("/");
            for (String part : pathParts) {
                if (part.startsWith("school_")) {
                    String schoolName = part.substring("school_".length());
                    return SpellSchools.getSchool(schoolName.toLowerCase());
                }
            }
        }
        return null;
    }

    public static boolean isElementalAffix(String affixId) {
        ResourceLocation affixResource = new ResourceLocation(affixId);
        String path = affixResource.getPath();
        return path.contains("elemental/");
    }
}