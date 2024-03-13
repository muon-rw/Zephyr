package dev.muon.zephyr.mixin;

import dev.muon.zephyr.Zephyr;
import dev.muon.zephyr.affixes.MagicTelepathicAffix;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixRegistry;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = AffixRegistry.class, remap = false)
public abstract class AffixRegistryMixin extends DynamicRegistry<Affix> {
    public AffixRegistryMixin(Logger logger, String path, boolean synced, boolean subtypes) {
        super(Zephyr.LOGGER, "zephyr_affixes", true, true);
    }
    @Inject(method = "registerBuiltinCodecs", at = @At("TAIL"))
    protected void register(CallbackInfo Info) {
         this.registerCodec(Zephyr.loc("telepathic"), MagicTelepathicAffix.CODEC);
    }
}