package cn.gtemc.datapacks.extension.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class Reflections {

    public static final Field field$MappedRegistry$frozen = requireNonNull(
            ReflectionUtils.getDeclaredField(MappedRegistry.class, boolean.class, 0)
    );

    public static final Field field$MappedRegistry$unregisteredIntrusiveHolders = requireNonNull(
            ReflectionUtils.getDeclaredField(MappedRegistry.class, Map.class, 5)
    );

    public static final Method method$Registry$registerForHolder = requireNonNull(
            ReflectionUtils.getStaticMethod(
                    Registry.class, Holder.Reference.class, Registry.class, ResourceLocation.class, Object.class
            )
    );

    public static final Method method$Holder$Reference$bindValue = requireNonNull(
            ReflectionUtils.getDeclaredMethod(
                    Holder.Reference.class, void.class, Object.class
            )
    );

    public static final Field field$Holder$Reference$tags = requireNonNull(
            ReflectionUtils.getDeclaredField(Holder.Reference.class, Set.class, 0)
    );
}
