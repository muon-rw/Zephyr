package dev.muon.zephyr;

import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.attributes.SpellAttributes;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class AffixSchoolMapper {
    private static final Map<String, MagicSchool> schoolMappings = new HashMap<>();

    static {
        schoolMappings.put("fire", MagicSchool.FIRE);
        schoolMappings.put("frost", MagicSchool.FROST);
        schoolMappings.put("lightning", MagicSchool.LIGHTNING);
        schoolMappings.put("arcane", MagicSchool.ARCANE);
        schoolMappings.put("soul", MagicSchool.SOUL);
        schoolMappings.put("healing", MagicSchool.HEALING);
    }
    public static EnumSet<MagicSchool> getMagicSchoolsFromGear(ItemStack stack, EquipmentSlot slot) {
        EnumSet<MagicSchool> schools = EnumSet.noneOf(MagicSchool.class);
        var attributes = stack.getAttributeModifiers(slot);
        for (var entry : attributes.entries()) {
            var attributeId = BuiltInRegistries.ATTRIBUTE.getKey(entry.getKey());
            for (var powerEntry : SpellAttributes.POWER.entrySet()) {
                if (powerEntry.getValue().id.equals(attributeId)) {
                    schools.add(powerEntry.getKey());
                }
            }
        }
        return schools;
    }

    public static EnumSet<MagicSchool> getAllRelevantMagicSchools(ItemStack stack) {
        EnumSet<MagicSchool> allSchools = EnumSet.noneOf(MagicSchool.class);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            allSchools.addAll(getMagicSchoolsFromGear(stack, slot));
        }
        return allSchools;
    }

    public static MagicSchool getMagicSchoolForAffix(Affix affix) {
        ResourceLocation affixId = affix.getId();
        for (Map.Entry<String, MagicSchool> entry : schoolMappings.entrySet()) {
            if (affixId.getPath().contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static boolean isElementalAffix(Affix affix) {
        ResourceLocation affixId = affix.getId();
        return affixId.getPath().contains("elemental/");
    }
}