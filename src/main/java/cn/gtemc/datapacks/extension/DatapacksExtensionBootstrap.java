package cn.gtemc.datapacks.extension;

import cn.gtemc.datapacks.extension.feature.Features;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class DatapacksExtensionBootstrap implements PluginBootstrap {
    private static DatapacksExtensionBootstrap instance;
    private ComponentLogger logger;

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        instance = this;
        this.logger = context.getLogger();
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.DATAPACK_DISCOVERY, (e) -> {
            try {
                this.logger.info("Registering datapacks extension...");
                Features.init();
            } catch (Throwable ex) {
                this.logger.warn("Failed to register", ex);
            }
        });
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new DatapacksExtension(this);
    }

    public ComponentLogger logger() {
        return logger;
    }

    public static DatapacksExtensionBootstrap instance() {
        if (instance == null) throw new IllegalStateException("DatapacksExtensionBootstrap is not initialized");
        return instance;
    }
}
