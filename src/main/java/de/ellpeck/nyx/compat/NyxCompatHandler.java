package de.ellpeck.nyx.compat;

import de.ellpeck.nyx.Nyx;
import de.ellpeck.nyx.compat.simpledifficulty.SimpleDifficulty;
import de.ellpeck.nyx.compat.tconstruct.ConstructsArmory;
import de.ellpeck.nyx.compat.tconstruct.TinkersConstruct;
import de.ellpeck.nyx.compat.tconstruct.TinkersConstructClient;
import de.ellpeck.nyx.compat.toughasnails.ToughAsNails;
import de.ellpeck.nyx.config.NyxConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

@EventBusSubscriber(modid = Nyx.ID)
public class NyxCompatHandler {
    public static void preInit() {
        if (Loader.isModLoaded("tconstruct") && NyxConfig.MOD_INTEGRATION.tinkersConstructIntegration) {
            TinkersConstruct.registerToolMaterials();
            if (FMLLaunchHandler.side().isClient()) {
                MinecraftForge.EVENT_BUS.register(new TinkersConstructClient());
            }

            // Only load Construct's Armory if Tinkers' Construct is also loaded
            if (Loader.isModLoaded("conarm") && NyxConfig.MOD_INTEGRATION.constructsArmoryIntegration) {
                ConstructsArmory.registerToolMaterials();
            }
        }
    }

    public static void init() {
        if (Loader.isModLoaded("simpledifficulty") && NyxConfig.MOD_INTEGRATION.simpleDifficultyIntegration) {
            SimpleDifficulty.registerTemperatureModifiers();
        }

        if (Loader.isModLoaded("tconstruct") && NyxConfig.MOD_INTEGRATION.tinkersConstructIntegration) {
            TinkersConstruct.registerToolRecipes();
        }
        if (Loader.isModLoaded("toughasnails") && NyxConfig.MOD_INTEGRATION.toughAsNailsIntegration) {
            ToughAsNails.registerTemperatureModifiers();
        }
    }

    public static void postInit() {
        if (Loader.isModLoaded("tconstruct") && NyxConfig.MOD_INTEGRATION.tinkersConstructIntegration) {
            TinkersConstruct.registerSmelteryRecipes();
        }
    }
}
