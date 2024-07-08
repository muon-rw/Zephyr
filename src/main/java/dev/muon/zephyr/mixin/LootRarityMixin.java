package dev.muon.zephyr.mixin;

import dev.muon.zephyr.AffixSchoolMapper;
import dev.muon.zephyr.LootCategories;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixType;
import dev.shadowsoffire.apotheosis.adventure.loot.LootController;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.SpellSchool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(value = LootRarity.LootRule.class, remap = false)
public abstract class LootRarityMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Ldev/shadowsoffire/apotheosis/adventure/loot/LootController;getAvailableAffixes(Lnet/minecraft/world/item/ItemStack;Ldev/shadowsoffire/apotheosis/adventure/loot/LootRarity;Ljava/util/Set;Ldev/shadowsoffire/apotheosis/adventure/affix/AffixType;)Ljava/util/List;"))
    private List<DynamicHolder<? extends Affix>> filterAffixes(ItemStack stack, LootRarity rarity, Set<DynamicHolder<? extends Affix>> currentAffixes, AffixType type) {
        List<DynamicHolder<? extends Affix>> availableAffixes = LootController.getAvailableAffixes(stack, rarity, currentAffixes, type);
        Set<SpellSchool> gearSpellSchools = AffixSchoolMapper.getSpellSchoolsFromWeapon(stack);

        return availableAffixes.stream()
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