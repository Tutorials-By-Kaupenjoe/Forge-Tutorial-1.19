package net.kaupenjoe.tutorialmod.world.feature;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RED_MAPLE_CHECKED_KEY = createKey("red_maple_checked");
    public static final ResourceKey<PlacedFeature> RED_MAPLE_PLACED_KEY = createKey("red_maple_placed");

    public static final ResourceKey<PlacedFeature> ZIRCON_PLACED_KEY = createKey("zircon_placed");
    public static final ResourceKey<PlacedFeature> END_ZIRCON_PLACED_KEY = createKey("end_zircon_placed");
    public static final ResourceKey<PlacedFeature> NETHER_ZIRCON_PLACED_KEY = createKey("nether_zircon_placed");

    public static final ResourceKey<PlacedFeature> ZIRCON_GEODE_PLACED_KEY = createKey("zircon_geode_placed");

    public static final ResourceKey<PlacedFeature> JASMINE_PLACED_KEY = createKey("jasmine_placed");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RED_MAPLE_CHECKED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_MAPLE_KEY),
                List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.RED_MAPLE_SAPLING.get())));
        register(context, RED_MAPLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_MAPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2)));

        register(context, ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ZIRCON_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, NETHER_ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ZIRCON_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, END_ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ZIRCON_ORE_KEY),
                commonOrePlacement(12, // VeinsPerChunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ZIRCON_GEODE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIRCON_GEODE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(50)),
                        BiomeFilter.biome()));

        register(context, JASMINE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.JASMINE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    }


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TutorialMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
