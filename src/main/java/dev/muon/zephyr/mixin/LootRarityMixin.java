package dev.muon.zephyr.mixin;


import dev.muon.zephyr.AffixSchoolMapper;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import net.spell_power.api.MagicSchool;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


@Mixin(value = LootRarity.LootRule.class, remap = false)
public abstract class LootRarityMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;", remap = false))
    private Stream<DynamicHolder<Affix>> filterAffixes(Stream<DynamicHolder<Affix>> stream, Predicate<DynamicHolder<Affix>> predicate, ItemStack stack, LootRarity rarity, Set<DynamicHolder<Affix>> currentAffixes) {
        return stream.filter(a -> {
            if (!predicate.test(a)) {
                return false;
            }
            Affix affix = a.get();
            if (AffixSchoolMapper.isElementalAffix(affix)) {
                EnumSet<MagicSchool> gearMagicSchools = AffixSchoolMapper.getAllRelevantMagicSchools(stack);
                MagicSchool affixMagicSchool = AffixSchoolMapper.getMagicSchoolForAffix(affix);
                if (!gearMagicSchools.isEmpty()) {
                    if (affixMagicSchool == null || !gearMagicSchools.contains(affixMagicSchool)) {
                        return false;
                    }
                }
            }
            return true;
        });
    }
}