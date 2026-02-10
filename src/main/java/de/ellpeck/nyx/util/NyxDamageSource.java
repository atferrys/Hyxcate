package de.ellpeck.nyx.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import javax.annotation.Nullable;

public class NyxDamageSource extends EntityDamageSource {
    public static final DamageSource CELESTIAL = new DamageSource("nyx_celestial").setDamageBypassesArmor().setDamageIsAbsolute();
    public static final DamageSource DEEP_FREEZE = new DamageSource("nyx_deep_freeze").setDamageBypassesArmor();
    public static final DamageSource INFERNO = new DamageSource("nyx_inferno").setDamageBypassesArmor(); // It's fire but we don't want fire immune mobs to completely protect against it lol
    public static final DamageSource LASER = new DamageSource("nyx_laser").setDamageBypassesArmor();
    public static final DamageSource PARALYSIS = new DamageSource("nyx_paralysis").setDamageBypassesArmor();

    public NyxDamageSource(String damageType, @Nullable Entity damageSourceEntity) {
        super(damageType, damageSourceEntity);
        this.damageType = damageType;
    }

    public static DamageSource causeIndirectLaserDamage(Entity source, @Nullable Entity indirectEntity) {
        return (new EntityDamageSourceIndirect("nyx_laser", source, indirectEntity)).setDamageBypassesArmor();
    }
}
