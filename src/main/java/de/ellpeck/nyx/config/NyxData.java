package de.ellpeck.nyx.config;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NyxData {
    public static final List<String> ALLOWED_DIMENSIONS_LUNAR = new ArrayList<>();
    public static final List<String> ALLOWED_DIMENSIONS_SOLAR = new ArrayList<>();

    public static final List<String> MOB_DUPLICATION_LIST = new ArrayList<>();

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

    public static void initConfigLists() {
        ALLOWED_DIMENSIONS_LUNAR.clear();
        ALLOWED_DIMENSIONS_LUNAR.addAll(Arrays.asList(NyxConfig.EVENTS_LUNAR.allowedDimensions));
        ALLOWED_DIMENSIONS_SOLAR.clear();
        ALLOWED_DIMENSIONS_SOLAR.addAll(Arrays.asList(NyxConfig.EVENTS_SOLAR.allowedDimensions));

        MOB_DUPLICATION_LIST.clear();
        MOB_DUPLICATION_LIST.addAll(Arrays.asList(NyxConfig.EVENTS_LUNAR.mobDuplicationList));

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
    }

    private static void readBlocksFromConfig(String[] array, List<Block> list) {
        list.clear();
        for (String string : array) {
            list.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(string)));
        }
    }
}
