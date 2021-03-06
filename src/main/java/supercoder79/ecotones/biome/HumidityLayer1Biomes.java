package supercoder79.ecotones.biome;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NoiseHeightmapDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import supercoder79.ecotones.features.EcotonesFeatures;
import supercoder79.ecotones.features.config.FeatureConfigHolder;
import supercoder79.ecotones.features.config.SimpleTreeFeatureConfig;
import supercoder79.ecotones.surface.EcotonesSurfaces;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class HumidityLayer1Biomes {
    public static Map<Double, Integer> Humidity2BiomeMap = new LinkedHashMap<>();

    public static Biome DESERT_BIOME;
    public static Biome SCRUBLAND_BIOME;
    public static Biome STEPPE_BIOME;
    public static Biome TROPICAL_GRASSLAND_BIOME;
    public static Biome LUSH_SAVANNAH_BIOME;
    public static Biome DRY_FOREST_BIOME;
    public static Biome LUSH_FOREST_BIOME;
    public static Biome TROPICAL_RAINFOREST_BIOME;

    private static EcotonesBiome.Template template = new EcotonesBiome.Template(EcotonesBiome.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_CONFIG)
            .precipitation(Biome.Precipitation.NONE).category(Biome.Category.FOREST)
            .depth(0.5F)
            .scale(0.075F)
            .temperature(2F)
            .downfall(0F)
            .waterColor(4159204)
            .waterFogColor(329011)
            .addDefaultFeatures(LAND_CARVERS, STRUCTURES, DUNGEONS, MINEABLES, ORES, DISKS,
                    DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
            .addStructureFeature(Feature.STRONGHOLD)
            .addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))

            .addStructureFeature(Feature.RUINED_PORTAL, new RuinedPortalFeatureConfig(RuinedPortalFeature.Type.STANDARD))

            .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                    Feature.RANDOM_PATCH.configure(FeatureConfigHolder.SURFACE_ROCKS).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_32.configure(new CountDecoratorConfig(1))))

            .addDefaultSpawnEntries());

    public static void init() {
        DESERT_BIOME = BiomeUtil.register(new Identifier("ecotones", "desert"), template.builder()
                .hilliness(1.6)
                .addStructureFeature(Feature.DESERT_PYRAMID, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.PILLAGER_OUTPOST, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.VILLAGE, new StructurePoolFeatureConfig("village/desert/town_centers", 8))
                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.CACTUS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(10)))));
        SCRUBLAND_BIOME = BiomeUtil.register(new Identifier("ecotones", "scrubland"), template.builder()
                .configureSurfaceBuilder(EcotonesSurfaces.DESERT_SCRUB_BUILDER, SurfaceBuilder.GRASS_CONFIG)
                .temperature(1.9F)
                .downfall(0.2F)
                .hilliness(1.6)
                .addDefaultFeature(PLAINS_TALL_GRASS)
                .addStructureFeature(Feature.PILLAGER_OUTPOST, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.VILLAGE, new StructurePoolFeatureConfig("village/desert/town_centers", 10))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.CACTUS_CONFIG)
                                .createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(40))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.5f, 2))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.5f, 3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.CACTI.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.SCRUBLAND_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.DESERT_GRASS_CONFIG).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_32.configure(new CountDecoratorConfig(3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.0625f, 1))))

                .addTreeFeature(Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.DEAD_BUSH_CONFIG), 3));
        STEPPE_BIOME = BiomeUtil.register( new Identifier("ecotones", "steppe"), template.builder()
                .temperature(1.8F)
                .downfall(0.3F)
                .hilliness(2.8)
                .volatility(0.88)
                .addDefaultFeature(PLAINS_TALL_GRASS)
                .addStructureFeature(Feature.PILLAGER_OUTPOST, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.VILLAGE, new StructurePoolFeatureConfig("village/savanna/town_centers", 4))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(7, 0.5f, 3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.5f, 3))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.25f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10))))

                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG));
        TROPICAL_GRASSLAND_BIOME = BiomeUtil.register(new Identifier("ecotones", "tropical_grassland"), template.builder()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .temperature(1.7F)
                .downfall(0.4F)
                .scale(0.12f)
                .hilliness(1.8)

                .addStructureFeature(Feature.PILLAGER_OUTPOST, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.VILLAGE, new StructurePoolFeatureConfig("village/savanna/town_centers", 10))

                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.25f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.125f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.BIG_SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.5f, 1))))

                .addTreeFeature(EcotonesFeatures.SMALL_ACACIA.configure(FeatureConfig.DEFAULT), 1)
                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(FeatureConfigHolder.MOSTLY_SHORT_GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 12)))));
        LUSH_SAVANNAH_BIOME = BiomeUtil.register( new Identifier("ecotones", "lush_savannah"), template.builder()
                .temperature(1.6F)
                .downfall(0.5F)
                .scale(0.15f)
                .hilliness(1.4)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .addStructureFeature(Feature.PILLAGER_OUTPOST, FeatureConfig.DEFAULT)
                .addStructureFeature(Feature.VILLAGE, new StructurePoolFeatureConfig("village/savanna/town_centers", 12))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.DESERTIFY_SOIL.configure(FeatureConfig.DEFAULT)
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.25f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 20))))

                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.ACACIA_TREE_CONFIG), 1)
                .addTreeFeature(EcotonesFeatures.SMALL_ACACIA.configure(FeatureConfig.DEFAULT), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG), 1));

        DRY_FOREST_BIOME = BiomeUtil.register(new Identifier("ecotones", "dry_forest"), template.builder()
                .temperature(1.6F)
                .downfall(0.6F)
                .scale(0.3f)
                .hilliness(2.1)
                .volatility(0.98)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 12, 20))))

                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG), 3)
                .addTreeFeature(EcotonesFeatures.SMALL_ACACIA.configure(FeatureConfig.DEFAULT), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.ACACIA_TREE_CONFIG), 1)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG), 1));

        LUSH_FOREST_BIOME = BiomeUtil.register(new Identifier("ecotones", "lush_forest"), template.builder()
                .temperature(1.6F)
                .downfall(0.8F)
                .scale(0.4f)
                .hilliness(2.3)
                .volatility(0.94)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 16, 20))))
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG), 4)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG), 1));

        TROPICAL_RAINFOREST_BIOME = BiomeUtil.register( new Identifier("ecotones", "tropical_rainforest"), template.builder()
                .temperature(1.6F)
                .downfall(1F)
                .scale(0.4f)
                .hilliness(3.4)
                .volatility(0.88)
                .addStructureFeature(Feature.JUNGLE_TEMPLE, FeatureConfig.DEFAULT)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
                .addDefaultFeatures(PLAINS_TALL_GRASS, PLAINS_FEATURES)
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        EcotonesFeatures.SHRUB.configure(new SimpleTreeFeatureConfig(Blocks.JUNGLE_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()))
                                .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.5f, 1))))

                .addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG).createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 20, 20))))
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG), 3)
                .addTreeFeature(EcotonesFeatures.JUNGLE_PALM_TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG), 3)
                .addTreeFeature(EcotonesFeatures.BANANA_TREE.configure(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG), 2)
                .addTreeFeature(Feature.TREE.configure(DefaultBiomeFeatures.MEGA_JUNGLE_TREE_CONFIG), 1));

        Humidity2BiomeMap.put(0.75, Registry.BIOME.getRawId(TROPICAL_RAINFOREST_BIOME));
        Humidity2BiomeMap.put(0.5, Registry.BIOME.getRawId(LUSH_FOREST_BIOME));
        Humidity2BiomeMap.put(0.25, Registry.BIOME.getRawId(DRY_FOREST_BIOME));
        Humidity2BiomeMap.put(0.1, Registry.BIOME.getRawId(LUSH_SAVANNAH_BIOME));
        Humidity2BiomeMap.put(-0.1, Registry.BIOME.getRawId(TROPICAL_GRASSLAND_BIOME));
        Humidity2BiomeMap.put(-0.25, Registry.BIOME.getRawId(STEPPE_BIOME));
        Humidity2BiomeMap.put(-0.5, Registry.BIOME.getRawId(SCRUBLAND_BIOME));
        Humidity2BiomeMap.put(-0.75, Registry.BIOME.getRawId(DESERT_BIOME));
    }
}
