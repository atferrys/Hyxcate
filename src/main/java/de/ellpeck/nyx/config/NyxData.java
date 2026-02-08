package de.ellpeck.nyx.config;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NyxData {
    public static final List<Integer> ALLOWED_DIMENSIONS_LUNAR = new ArrayList<>();
    public static final List<Integer> ALLOWED_DIMENSIONS_SOLAR = new ArrayList<>();

    public static final List<Block> METEOR_BLOCKS_METEORITE = new ArrayList<>();
    public static final List<Block> METEOR_BLOCKS_FREZARITE = new ArrayList<>();
    public static final List<Block> METEOR_BLOCKS_KREKNORITE = new ArrayList<>();
    public static final List<Block> METEOR_BLOCKS_UNKNOWN = new ArrayList<>();

    public static final List<Block> FILLER_BLOCKS_METEORITE = new ArrayList<>();
    public static final List<Block> FILLER_BLOCKS_FREZARITE = new ArrayList<>();
    public static final List<Block> FILLER_BLOCKS_KREKNORITE = new ArrayList<>();
    public static final List<Block> FILLER_BLOCKS_UNKNOWN = new ArrayList<>();

    public static final List<Block> LIQUID_BLOCKS_METEORITE = new ArrayList<>();
    public static final List<Block> LIQUID_BLOCKS_FREZARITE = new ArrayList<>();
    public static final List<Block> LIQUID_BLOCKS_KREKNORITE = new ArrayList<>();
    public static final List<Block> LIQUID_BLOCKS_UNKNOWN = new ArrayList<>();

    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_BLOOD_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_BLUE_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_FULL_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_STAR_SHOWER = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_GRIM_ECLIPSE = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> EXTRA_SPAWNS_RED_GIANT = new Object2ObjectOpenHashMap<>();

    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_BLOOD_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_BLUE_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_FULL_MOON = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_STAR_SHOWER = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_GRIM_ECLIPSE = new Object2ObjectOpenHashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> REPLACEMENT_SPAWNS_RED_GIANT = new Object2ObjectOpenHashMap<>();

    public static void initConfigLists() {
        ALLOWED_DIMENSIONS_LUNAR.clear();
        ALLOWED_DIMENSIONS_LUNAR.addAll(Arrays.asList(NyxConfig.EVENTS_LUNAR.allowedDimensions));
        ALLOWED_DIMENSIONS_SOLAR.clear();
        ALLOWED_DIMENSIONS_SOLAR.addAll(Arrays.asList(NyxConfig.EVENTS_SOLAR.allowedDimensions));

        readBlocksFromConfig(NyxConfig.METEORS.meteorBlocksMeteorite, METEOR_BLOCKS_METEORITE);
        readBlocksFromConfig(NyxConfig.METEORS.meteorBlocksFrezarite, METEOR_BLOCKS_FREZARITE);
        readBlocksFromConfig(NyxConfig.METEORS.meteorBlocksKreknorite, METEOR_BLOCKS_KREKNORITE);
        readBlocksFromConfig(NyxConfig.METEORS.meteorBlocksUnknown, METEOR_BLOCKS_UNKNOWN);

        readBlocksFromConfig(NyxConfig.METEORS.fillerBlocksMeteorite, FILLER_BLOCKS_METEORITE);
        readBlocksFromConfig(NyxConfig.METEORS.fillerBlocksFrezarite, FILLER_BLOCKS_FREZARITE);
        readBlocksFromConfig(NyxConfig.METEORS.fillerBlocksKreknorite, FILLER_BLOCKS_KREKNORITE);
        readBlocksFromConfig(NyxConfig.METEORS.fillerBlocksUnknown, FILLER_BLOCKS_UNKNOWN);

        readBlocksFromConfig(NyxConfig.METEORS.liquidBlocksMeteorite, LIQUID_BLOCKS_METEORITE);
        readBlocksFromConfig(NyxConfig.METEORS.liquidBlocksFrezarite, LIQUID_BLOCKS_FREZARITE);
        readBlocksFromConfig(NyxConfig.METEORS.liquidBlocksKreknorite, LIQUID_BLOCKS_KREKNORITE);
        readBlocksFromConfig(NyxConfig.METEORS.liquidBlocksUnknown, LIQUID_BLOCKS_UNKNOWN);

        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.BLOOD_MOON.spawnsExtra, EXTRA_SPAWNS_BLOOD_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.BLUE_MOON.spawnsExtra, EXTRA_SPAWNS_BLUE_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.FULL_MOON.spawnsExtra, EXTRA_SPAWNS_FULL_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.STAR_SHOWER.spawnsExtra, EXTRA_SPAWNS_STAR_SHOWER);
        readEntitiesFromConfig(NyxConfig.EVENTS_SOLAR.GRIM_ECLIPSE.spawnsExtra, EXTRA_SPAWNS_GRIM_ECLIPSE);
        readEntitiesFromConfig(NyxConfig.EVENTS_SOLAR.RED_GIANT.spawnsExtra, EXTRA_SPAWNS_RED_GIANT);

        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.BLOOD_MOON.spawnsReplacement, REPLACEMENT_SPAWNS_BLOOD_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.BLUE_MOON.spawnsReplacement, REPLACEMENT_SPAWNS_BLUE_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.FULL_MOON.spawnsReplacement, REPLACEMENT_SPAWNS_FULL_MOON);
        readEntitiesFromConfig(NyxConfig.EVENTS_LUNAR.STAR_SHOWER.spawnsReplacement, REPLACEMENT_SPAWNS_STAR_SHOWER);
        readEntitiesFromConfig(NyxConfig.EVENTS_SOLAR.GRIM_ECLIPSE.spawnsReplacement, REPLACEMENT_SPAWNS_GRIM_ECLIPSE);
        readEntitiesFromConfig(NyxConfig.EVENTS_SOLAR.RED_GIANT.spawnsReplacement, REPLACEMENT_SPAWNS_RED_GIANT);
    }

    private static void readBlocksFromConfig(String[] array, List<Block> list) {
        list.clear();
        for (String string : array) {
            list.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string)));
        }
    }

    private static void readEntitiesFromConfig(String[] array, Map<ResourceLocation, ResourceLocation> map) {
        map.clear();
        for (String string : array) {
            String[] subStrings = string.split(";");
            if (subStrings.length != 2) continue;
            map.put(new ResourceLocation(subStrings[0]), new ResourceLocation(subStrings[1]));
        }
    }
}
