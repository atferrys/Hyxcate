package de.ellpeck.nyx.compat.peacefulsurface.mixin.common;

import de.ellpeck.nyx.capability.NyxWorld;
import de.ellpeck.nyx.config.NyxConfig;
import lain.mods.peacefulsurface.PeacefulSurface;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PeacefulSurface.class, remap = false)
public abstract class PSEntitySpawnFilterMixin {
    @Inject(method = "CheckSpawn", at = @At("HEAD"), cancellable = true)
    private void psCheckSpawn(LivingSpawnEvent.CheckSpawn event, CallbackInfo ci) {
        if (NyxConfig.MOD_INTEGRATION.peacefulSurfaceIntegration) {
            NyxWorld nyx = NyxWorld.get(event.getWorld());
            if (nyx != null && (nyx.currentSolarEvent != null || nyx.currentLunarEvent != null)) {
                ci.cancel();
            }
        }
    }
}
