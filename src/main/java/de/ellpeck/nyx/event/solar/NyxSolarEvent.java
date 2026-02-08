package de.ellpeck.nyx.event.solar;

import de.ellpeck.nyx.capability.NyxWorld;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class NyxSolarEvent implements INBTSerializable<NBTTagCompound> {

    public final String name;
    protected final NyxWorld nyxWorld;
    protected final World world;

    protected NyxSolarEvent(String name, NyxWorld nyxWorld) {
        this.name = name;
        this.nyxWorld = nyxWorld;
        this.world = nyxWorld.world;
    }

    public abstract ITextComponent getStartMessage();

    public SoundEvent getStartSound() {
        return null;
    }

    public abstract boolean shouldStart(boolean lastNighttime);

    public abstract boolean shouldStop(boolean lastNighttime);

    public boolean shouldStartBasic(boolean lastNighttime) {
        return lastNighttime && !NyxWorld.isNighttime(this.world);
    }

    public int getSkyColor() {
        return 0;
    }

    public int getLightmapColor() {
        return 0;
    }

    public String getSunTexture() {
        return null;
    }

    public void update(boolean lastNighttime) {
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    public class ConfigImpl implements INBTSerializable<NBTTagCompound> {

        public int daysSinceLast;
        public int startDays;
        public int graceDays;

        public double chance;
        public int startDay;
        public int gracePeriod;
        public int dayInterval;

        public ConfigImpl(double chance, int startDay, int gracePeriod, int dayInterval) {
            this.chance = chance;
            this.startDay = startDay;
            this.gracePeriod = gracePeriod;
            this.dayInterval = dayInterval;
        }

        public void update(boolean lastNighttime) {
            if (NyxSolarEvent.this.nyxWorld.currentSolarEvent == NyxSolarEvent.this) {
                this.daysSinceLast = 0;
                this.graceDays = 0;
            }

            if (!lastNighttime && NyxWorld.isNighttime(NyxSolarEvent.this.world)) {
                this.daysSinceLast++;
                if (this.startDays < this.startDay) this.startDays++;
                if (this.graceDays < this.gracePeriod) this.graceDays++;
            }
        }

        public boolean canStart() {
            if (NyxSolarEvent.this.nyxWorld.forcedSolarEvent == NyxSolarEvent.this) return true;
            if (this.startDays < this.startDay) return false;
            if (this.graceDays < this.gracePeriod) return false;
            if (this.dayInterval > 0) {
                return this.daysSinceLast >= this.dayInterval;
            } else {
                return NyxSolarEvent.this.world.rand.nextDouble() <= this.chance;
            }
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("days_since_last", this.daysSinceLast);
            compound.setInteger("start_days", this.startDays);
            compound.setInteger("grace_days", this.graceDays);
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            this.daysSinceLast = compound.getInteger("days_since_last");
            this.startDays = compound.getInteger("start_days");
            this.graceDays = compound.getInteger("grace_days");
        }
    }
}
