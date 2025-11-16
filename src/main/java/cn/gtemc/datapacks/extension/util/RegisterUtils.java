package cn.gtemc.datapacks.extension.util;

import cn.gtemc.datapacks.extension.DatapacksExtensionBootstrap;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Set;

public class RegisterUtils {

    public static void registerFeature(ResourceLocation location, Feature<?> feature) {
        try {
            Holder.Reference<Feature<?>> holder = Registry.registerForHolder(BuiltInRegistries.FEATURE, location, feature);
            Reflections.method$Holder$Reference$bindValue.invoke(holder, feature);
            Reflections.field$Holder$Reference$tags.set(holder, Set.of());
        } catch (Throwable e) {
            DatapacksExtensionBootstrap.instance().logger().warn("Failed to register feature", e);
        }
    }

    public static void unfreezeRegistry(Registry<?> registries) {
        if (!(registries instanceof MappedRegistry<?> mappedRegistry)) return;
        try {
            Reflections.field$MappedRegistry$frozen.set(mappedRegistry, false);
        } catch (Throwable e) {
            DatapacksExtensionBootstrap.instance().logger().warn("Failed to unfreeze registry", e);
        }
    }

    public static void freezeRegistry(Registry<?> registries) {
        if (!(registries instanceof MappedRegistry<?> mappedRegistry)) return;
        try {
            Reflections.field$MappedRegistry$frozen.set(mappedRegistry, true);
        } catch (Throwable e) {
            DatapacksExtensionBootstrap.instance().logger().warn("Failed to freeze registry", e);
        }
    }
}
