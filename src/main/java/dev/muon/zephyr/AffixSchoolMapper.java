package dev.muon.zephyr;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
import net.spell_power.api.enchantment.SpellPowerEnchanting;

import java.util.Set;

public class AffixSchoolMapper {

    public static Set<SpellSchool> getSpellSchoolsFromGear(ItemStack stack, EquipmentSlot slot) {
        return SpellPowerEnchanting.relevantSchools(stack, slot);
    }
    public static Set<SpellSchool> getSpellSchoolsFromGear(ItemStack stack) {
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