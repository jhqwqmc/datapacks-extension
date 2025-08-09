package cn.gtemc.datapacks.extension.feature;

import cn.gtemc.datapacks.extension.util.RegisterUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class Features {
    public static final ResourceLocation TEST_FEATURE = ResourceLocation.fromNamespaceAndPath("gtemc", "test_feature");

    public static void init() {
        RegisterUtils.unfreezeRegistry(BuiltInRegistries.FEATURE);
        RegisterUtils.registerFeature(TEST_FEATURE, new TestFeature(NoneFeatureConfiguration.CODEC));
        RegisterUtils.freezeRegistry(BuiltInRegistries.FEATURE);
    }
}
