package de.ellpeck.nyx.compat.urkazmoontools.mixin.client;

import com.urkaz.moontools.item.MoonClockItem;
import de.ellpeck.nyx.capability.NyxWorld;
import de.ellpeck.nyx.event.lunar.NyxEventBloodMoon;
import de.ellpeck.nyx.event.lunar.NyxEventBlueMoon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(MoonClockItem.class)
public abstract class UMTMoonClockItemMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/urkaz/moontools/item/MoonClockItem;addPropertyOverride(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/item/IItemPropertyGetter;)V"))
    public void nyxUMTMoonClockItem(MoonClockItem instance, ResourceLocation resourceLocation, IItemPropertyGetter iItemPropertyGetter) {
        instance.addPropertyOverride(new ResourceLocation("moonphase"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                boolean flag = entityIn != null;
                Entity entity = flag ? entityIn : stack.getItemFrame();

                if (worldIn == null && entity != null) {
                    worldIn = entity.world;
                }

                if (worldIn == null) {
                    return 0;
                } else {
                    int moonFactor;
                    if (worldIn.provider.isSurfaceWorld()) {
                        int extras = 0;
                        NyxWorld nyx = NyxWorld.get(worldIn);
                        if (nyx != null && nyx.currentLunarEvent instanceof NyxEventBlueMoon) {
                            extras += 100;
                        }
                        if (nyx != null && nyx.currentLunarEvent instanceof NyxEventBloodMoon) {
                            extras += 10;
                        }

                        moonFactor = worldIn.provider.getMoonPhase(worldIn.getWorldTime()) + extras;
                    } else {
                        double randomDouble = Math.random();
                        randomDouble = randomDouble * 8;
                        moonFactor = (int) randomDouble;
                    }
                    return moonFactor;
                }
            }
        });
    }
}
