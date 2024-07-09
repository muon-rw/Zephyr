package dev.muon.zephyr.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.muon.zephyr.AffixSchoolMapper;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixType;
import dev.shadowsoffire.apotheosis.adventure.loot.LootController;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.SpellSchool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(value = LootController.class, remap = false)
public class LootControllerMixin {

    @ModifyReturnValue(method = "getAvailableAffixes", at = @At("RETURN"))
    private static List<DynamicHolder<? extends Affix>> filterAffixes(List<DynamicHolder<? extends Affix>> originalAffixes, ItemStack stack, LootRarity rarity, Set<DynamicHolder<? extends Affix>> currentAffixes, AffixType type) {
        Set<SpellSchool> gearSpellSchools = AffixSchoolMapper.getSpellSchoolsFromWeapon(stack);

        return originalAffixes.stream()
                .filter(a -> {
                    Affix affix = a.get();
                    String affixId = affix.getId().toString();
                    if (AffixSchoolMapper.isElementalAffix(affixId)) {
                        SpellSchool affixSpellSchool = AffixSchoolMapper.getSpellSchoolForAffix(affixId);
                        return affixSpellSchool != null && gearSpellSchools.contains(affixSpellSchool);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
