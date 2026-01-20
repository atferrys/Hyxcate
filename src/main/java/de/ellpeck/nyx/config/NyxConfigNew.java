package de.ellpeck.nyx.config;

import de.ellpeck.nyx.Nyx;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Nyx.ID, name = Nyx.NAME + "_WIP")
public class NyxConfigNew {
    @Config.Comment("Master switches for entire modules")
    public static final MasterSwitches MASTER_SWITCHES = new MasterSwitches();

    @Config.Comment("Settings for general mechanics")
    public static final General GENERAL = new General();

    @Config.Comment("Settings for lunar events")
    public static final EventsLunar EVENTS_LUNAR = new EventsLunar();

    @Config.Comment("Settings for solar events")
    public static final EventsSolar EVENTS_SOLAR = new EventsSolar();

    @Config.Comment("Settings for meteors")
    public static final Meteors METEORS = new Meteors();

    @Config.Comment("Settings for falling stars")
    public static final FallingStars FALLING_STARS = new FallingStars();

    public static class MasterSwitches {
        @Config.Name("Events: Lunar")
        @Config.Comment("If lunar events should be enabled")
        public boolean lunarEventsEnabled = true;

        @Config.Name("Events: Solar")
        @Config.Comment("If solar events should be enabled")
        public boolean solarEventsEnabled = true;

        @Config.Name("Meteors")
        @Config.Comment("If meteors falling from the sky should be enabled")
        public boolean meteorsEnabled = true;

        @Config.Name("Falling Stars")
        @Config.Comment("If falling stars (including during star showers) should be enabled")
        public boolean fallingStarsEnabled = true;

        @Config.Name("Enchantments")
        @Config.Comment("If enchantments should be enabled")
        public boolean enchantmentsEnabled = true;
    }

    public static class General {
        @Config.Name("Celestial Warhammer Ability Damage")
        @Config.Comment("The amount of damage that the celestial warhammer deals if the maximum flight time was used")
        public int celestialWarhammerAbilityDamage = 32;

        @Config.Name("Event Tint")
        @Config.Comment("If celestial events should tint the sky")
        public boolean eventTint = true;

        @Config.Name("Event Tint Underground")
        @Config.Comment("If celestial events should also tint underground areas (can look slightly jarring when disabled)")
        public boolean eventTintUnderground = true;

        @Config.Name("Event Notifications")
        @Config.Comment("If celestial events should be announced in chat when they start")
        public boolean eventNotifications = true;

        @Config.Name("Lunar Edge Damage: Base")
        @Config.Comment("The amount of additional damage that will always be applied regardless of moon phase")
        public int lunarEdgeDamageBase = 0;

        @Config.Name("Lunar Edge Damage: Min Level")
        @Config.Comment("The amount of additional damage that should be applied to an item with level 1 lunar edge on a full moon")
        public double lunarEdgeDamageMinLevel = 1.0;

        @Config.Name("Lunar Edge Damage: Max Level")
        @Config.Comment("The amount of additional damage that should be applied to an item with max level lunar edge on a full moon")
        public double lunarEdgeDamageMaxLevel = 3.0;

        @Config.Name("Lunar Edge Max XP Multiplier")
        @Config.Comment({"The max multiplier on the amount of XP added (which happens during a full moon)",
                "Can be set to 0 to disable lunar edge XP gains",
                "The multiplier scales up to the max according to the level and moon phase",
                "Example: If the config option is set to 2.5, a full moon with max lunar edge level would give 3.5x XP and a new moon would give 1x XP"})
        public double lunarEdgeMaxXPMultiplier = 1.0;
    }

    public static class EventsLunar {
        @Config.Comment("Blood Moon settings")
        public final BloodMoon BLOOD_MOON = new BloodMoon();

        @Config.Comment("Blue Moon settings")
        public final BlueMoon BLUE_MOON = new BlueMoon();

        @Config.Comment("Full Moon settings")
        public final FullMoon FULL_MOON = new FullMoon();

        @Config.Comment("Star Shower settings")
        public final StarShower STAR_SHOWER = new StarShower();

        @Config.Name("Allowed Dimensions")
        @Config.Comment("Names of the dimensions that lunar events should occur in")
        public String[] allowedDimensions = new String[]{"overworld"};

        @Config.Name("Mob Duplication List")
        @Config.Comment({"The registry names of entities that should not be spawned during the full and blood moons", "If isMobDuplicationWhitelist is true, this acts as a whitelist instead"})
        public String[] mobDuplicationList = new String[0];

        @Config.Name("Is Mob Duplication Whitelist")
        @Config.Comment("If the mobDuplicationBlacklist should act as a whitelist instead")
        public boolean isMobDuplicationWhitelist = false;

        public static class BloodMoon {
            @Config.Name("Enabled")
            @Config.Comment("If the Blood Moon should be enabled")
            public boolean enabled = true;

            @Config.Name("Chance")
            @Config.Comment("The chance in percent (1 = 100%) of the Blood Moon occurring")
            @Config.RangeDouble(min = 0.0, max = 1.0)
            public double chance = 0.05;

            @Config.Name("Start Night")
            @Config.Comment("The amount of nights that should pass before the Blood Moon occurs for the first time")
            @Config.RangeInt(min = 0)
            public int startNight = 0;

            @Config.Name("Night Interval")
            @Config.Comment({"The interval in nights at which the Blood Moon should occur", "Overrides chance setting if set to a value greater than 0"})
            @Config.RangeInt(min = 0)
            public int nightInterval = 0;

            @Config.Name("Grace Period")
            @Config.Comment("The amount of nights that should pass until the Blood Moon happens again")
            @Config.RangeInt(min = 0)
            public int gracePeriod = 8;

            @Config.Name("On Full Moon")
            @Config.Comment("If the blood moon should only occur on full moon nights")
            public boolean onFullMoon = true;

            @Config.Name("Sleeping")
            @Config.Comment("If sleeping is allowed during a blood moon")
            public boolean sleeping = false;

            @Config.Name("Spawn Multiplier")
            @Config.Comment("The multiplier with which mobs should spawn during the blood moon (eg 2 means 2 mobs spawn instead of 1)")
            @Config.RangeInt(min = 1, max = 1000)
            public int spawnMultiplier = 2;

            @Config.Name("Spawn Radius")
            @Config.Comment({"The closest distance that mobs can spawn away from a player during the blood moon", "Vanilla value is 24"})
            public int spawnRadius = 20;

            @Config.Name("Mobs Vanish")
            @Config.Comment("If mobs spawned by the blood moon should die at sunup")
            public boolean mobsVanish = true;
        }

        public static class BlueMoon {
            @Config.Name("Enabled")
            @Config.Comment("If the Blue Moon should be enabled")
            public boolean enabled = true;

            @Config.Name("Chance")
            @Config.Comment("The chance in percent (1 = 100%) of the Blue Moon occurring")
            @Config.RangeDouble(min = 0.0, max = 1.0)
            public double chance = 0.05;

            @Config.Name("Start Night")
            @Config.Comment("The amount of nights that should pass before the Blue Moon occurs for the first time")
            @Config.RangeInt(min = 0)
            public int startNight = 0;

            @Config.Name("Night Interval")
            @Config.Comment({"The interval in nights at which the Blue Moon should occur", "Overrides chance setting if set to a value greater than 0"})
            @Config.RangeInt(min = 0)
            public int nightInterval = 0;

            @Config.Name("Grace Period")
            @Config.Comment("The amount of nights that should pass until the Blue Moon happens again")
            @Config.RangeInt(min = 0)
            public int gracePeriod = 8;

            @Config.Name("On Full Moon")
            @Config.Comment("If the Blue Moon should only occur on full moon nights")
            public boolean onFullMoon = true;

            @Config.Name("Grow Amount")
            @Config.Comment("The amount of plants that should be grown per chunk during the Blue Moon")
            @Config.RangeInt(min = 0, max = 100)
            public int growAmount = 15;

            @Config.Name("Grow Interval")
            @Config.Comment("The amount of ticks that should pass before plants are grown again during the Blue Moon")
            @Config.RangeInt(min = 1, max = 100)
            public int growInterval = 10;
        }

        public static class FullMoon {
            @Config.Name("Enabled")
            @Config.Comment("If the vanilla full moon should be considered a proper lunar event")
            public boolean enabled = true;

            @Config.Name("Add Potion Effects")
            @Config.Comment("If mobs spawned during a full moon should have random potion effects applied to them (similarly to spiders in the base game)")
            public boolean addPotionEffects = true;

            @Config.Name("Additional Mobs Chance")
            @Config.Comment({"The chance for an additional mob to be spawned when a mob spawns during a full moon", "The higher the number, the less likely", "Set to 0 to disable"})
            @Config.RangeInt(min = 0, max = 1000)
            public int additionalMobsChance = 5;
        }

        public static class StarShower {
            @Config.Name("Enabled")
            @Config.Comment("If Star Showers should be enabled")
            public boolean enabled = true;

            @Config.Name("Chance")
            @Config.Comment("The chance in percent (1 = 100%) of Star Showers occurring")
            @Config.RangeDouble(min = 0.0, max = 1.0)
            public double chance = 0.05;

            @Config.Name("Start Night")
            @Config.Comment("The amount of nights that should pass before Star Showers occurs for the first time")
            @Config.RangeInt(min = 0)
            public int startNight = 0;

            @Config.Name("Night Interval")
            @Config.Comment({"The interval in nights at which Star Showers should occur", "Overrides chance setting if set to a value greater than 0"})
            @Config.RangeInt(min = 0)
            public int nightInterval = 0;

            @Config.Name("Grace Period")
            @Config.Comment("The amount of nights that should pass until Star Showers happen again")
            @Config.RangeInt(min = 0)
            public int gracePeriod = 15;
        }
    }

    public static class EventsSolar {
        @Config.Comment("Grim Eclipse settings")
        public final GrimEclipse GRIM_ECLIPSE = new GrimEclipse();

        @Config.Comment("Red Giant settings")
        public final RedGiant RED_GIANT = new RedGiant();

        @Config.Name("Allowed Dimensions")
        @Config.Comment("Names of the dimensions that solar events should occur in")
        public String[] allowedDimensions = new String[]{"overworld"};

        public static class GrimEclipse {
            @Config.Name("Enabled")
            @Config.Comment("If the Grim Eclipse should be enabled")
            public boolean enabled = true;

            @Config.Name("Chance")
            @Config.Comment("The chance in percent (1 = 100%) of the Grim Eclipse occurring")
            @Config.RangeDouble(min = 0.0, max = 1.0)
            public double chance = 0.05;

            @Config.Name("Start Day")
            @Config.Comment("The amount of days that should pass before the Grim Eclipse occurs for the first time")
            @Config.RangeInt(min = 0)
            public int startDay = 0;

            @Config.Name("Day Interval")
            @Config.Comment({"The interval in days at which the Grim Eclipse should occur", "Overrides chance setting if set to a value greater than 0"})
            @Config.RangeInt(min = 0)
            public int dayInterval = 0;

            @Config.Name("Grace Period")
            @Config.Comment("The amount of days that should pass until the Grim Eclipse happens again")
            @Config.RangeInt(min = 0)
            public int gracePeriod = 8;
        }

        public static class RedGiant {
            @Config.Name("Enabled")
            @Config.Comment("If the Red Giant should be enabled")
            public boolean enabled = true;

            @Config.Name("Chance")
            @Config.Comment("The chance in percent (1 = 100%) of the Red Giant occurring")
            @Config.RangeDouble(min = 0.0, max = 1.0)
            public double chance = 0.05;

            @Config.Name("Start Day")
            @Config.Comment("The amount of days that should pass before the Red Giant occurs for the first time")
            @Config.RangeInt(min = 0)
            public int startDay = 0;

            @Config.Name("Day Interval")
            @Config.Comment({"The interval in days at which the Red Giant should occur", "Overrides chance setting if set to a value greater than 0"})
            @Config.RangeInt(min = 0)
            public int dayInterval = 0;

            @Config.Name("Grace Period")
            @Config.Comment("The amount of days that should pass until the Red Giant happens again")
            @Config.RangeInt(min = 0)
            public int gracePeriod = 15;
        }
    }

    public static class FallingStars {
        @Config.Name("Rarity")
        @Config.Comment("The chance in percent (1 = 100%) for a falling star to appear at night for each player per second")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double rarity = 0.005;

        @Config.Name("Rarity During Showers")
        @Config.Comment("The chance for a falling star to appear during a star shower for each player per second")
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double rarityShower = 0.05;

        @Config.Name("Ambient Volume")
        @Config.Comment("The volume for the falling star ambient sound")
        public double volumeAmbient = 8.0;

        @Config.Name("Impact Volume")
        @Config.Comment("The volume for the falling star impact sound")
        public double volumeImpact = 8.0;
    }

    public static class Meteors {
        @Config.Name("Chance")
        @Config.Comment("The chance of a meteor spawning every second, during the day")
        @Config.RangeDouble(min = 0.0)
        public double chance = 0.00007;

        @Config.Name("Chance At Night")
        @Config.Comment("The chance of a meteor spawning every second, during nighttime")
        @Config.RangeDouble(min = 0.0)
        public double chanceNight = 0.0012;

        @Config.Name("Gate Dimension")
        @Config.Comment("The dimension that needs to be entered to increase the spawning of meteors")
        public String gateDimension = "the_end";

        @Config.Name("Chance After Gate Visit")
        @Config.Comment("The chance of a meteor spawning every second, during the day, after the gate dimension has been entered once")
        @Config.RangeDouble(min = 0.0)
        public double chanceAfterGate = 0.0001;

        @Config.Name("Chance After Gate Visit At Night")
        @Config.Comment("The chance of a meteor spawning every second, during the night, after the gate dimension has been entered once")
        @Config.RangeDouble(min = 0.0)
        public double chanceAfterGateNight = 0.0015;

        @Config.Name("Chance During Star Showers")
        @Config.Comment("The chance of a meteor spawning every second, during a star shower")
        @Config.RangeDouble(min = 0.0)
        public double chanceStarShower = 0.0024;

        @Config.Name("Chance In The End")
        @Config.Comment("The chance of a meteor spawning every second, in the end dimension")
        @Config.RangeDouble(min = 0.0)
        public double chanceEnd = 0.0015;

        @Config.Name("Spawn Radius")
        @Config.Comment("The amount of blocks a meteor can spawn away from the nearest player")
        public int spawnRadius = 1000;

        @Config.Name("Disallow Radius")
        @Config.Comment("The radius in chunks that should be marked as invalid for meteor spawning around each player")
        public int disallowRadius = 14;

        @Config.Name("Disallow Time")
        @Config.Comment("The amount of ticks that need to pass for each player until the chance of a meteor spawning in the area is halved (and then halved again, and so on)")
        public int disallowTime = 12000;

        @Config.Name("Chat Message")
        @Config.Comment("If fallen meteors should be announced in chat on impact")
        public boolean message = true;

        @Config.Name("Chat Message Verbose")
        @Config.Comment("If chat messages for meteor impacts should include coordinates")
        public boolean messageVerbose = false;
    }

    @Mod.EventBusSubscriber(modid = Nyx.ID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Nyx.ID)) {
                ConfigManager.sync(Nyx.ID, Config.Type.INSTANCE);
            }
        }
    }
}
