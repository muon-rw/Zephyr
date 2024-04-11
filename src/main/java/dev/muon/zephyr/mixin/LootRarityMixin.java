package dev.muon.zephyr.mixin;

import dev.muon.zephyr.AffixSchoolMapper;
import dev.muon.zephyr.LootCategories;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.world.item.ItemStack;
import net.spell_power.api.SpellSchool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(value = LootRarity.LootRule.class, remap = false)
public abstract class LootRarityMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;", remap = false))
    private Stream<DynamicHolder<Affix>> filterAffixes(Stream<DynamicHolder<Affix>> stream, Predicate<DynamicHolder<Affix>> predicate, ItemStack stack, LootRarity rarity, Set<DynamicHolder<Affix>> currentAffixes) {
        if (LootCategories.isStaff(stack) || LootCategories.isSpellWeapon(stack)) {
            Set<SpellSchool> gearSpellSchools = AffixSchoolMapper.getSpellSchoolsFromGear(stack);
            return stream.filter(a -> {
                if (!predicate.test(a)) {
                    return false;
                }
                Affix affix = a.get();
                String affixId = affix.getId().toString();
                if (AffixSchoolMapper.isElementalAffix(affixId)) {
                    SpellSchool affixSpellSchool = AffixSchoolMapper.getSpellSchoolForAffix(affixId);
                    return affixSpellSchool != null && gearSpellSchools.contains(affixSpellSchool);
                }
                return true;
            });
        }
        return stream.filter(predicate);
    }
}