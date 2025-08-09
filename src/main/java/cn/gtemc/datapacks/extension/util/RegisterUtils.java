package cn.gtemc.datapacks.extension.util;

import cn.gtemc.datapacks.extension.DatapacksExtensionBootstrap;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.IdentityHashMap;

public class RegisterUtils {

    public static void registerFeature(ResourceLocation location, Feature<?> feature) {
        try {
            BuiltInRegistries.FEATURE.createIntrusiveHolder(feature);
            Registry.register(BuiltInRegistries.FEATURE, location, feature);
        } catch (Throwable e) {
            DatapacksExtensionBootstrap.instance().logger().warn("Failed to register feature", e);
        }
    }

    public static void unfreezeRegistry(Registry<?> registries) {
        if (!(registries instanceof MappedRegistry<?> mappedRegistry)) return;
        try {
            Reflections.field$MappedRegistry$frozen.set(mappedRegistry, false);
            Reflections.field$MappedRegistry$unregisteredIntrusiveHolders.set(mappedRegistry, new IdentityHashMap<>());
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
