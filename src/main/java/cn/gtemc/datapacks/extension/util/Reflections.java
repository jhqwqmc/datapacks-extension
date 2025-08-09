package cn.gtemc.datapacks.extension.util;

import net.minecraft.core.MappedRegistry;

import java.lang.reflect.Field;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class Reflections {

    public static final Field field$MappedRegistry$frozen = requireNonNull(
            ReflectionUtils.getDeclaredField(MappedRegistry.class, boolean.class, 0)
    );

    public static final Field field$MappedRegistry$unregisteredIntrusiveHolders = requireNonNull(
            ReflectionUtils.getDeclaredField(MappedRegistry.class, Map.class, 5)
    );
}
