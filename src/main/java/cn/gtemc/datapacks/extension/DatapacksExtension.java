package cn.gtemc.datapacks.extension;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;

public final class DatapacksExtension extends JavaPlugin {
    private final DatapacksExtensionBootstrap bootstrap;
    private static DatapacksExtension instance;

    public DatapacksExtension(DatapacksExtensionBootstrap bootstrap) {
        instance = this;
        this.bootstrap = bootstrap;
    }

    @Override
    public void onLoad() {
        logger().info("Loading datapacks extension...");
    }

    @Override
    public void onEnable() {
        logger().info("Enabling datapacks extension...");
    }

    @Override
    public void onDisable() {
        logger().info("Disabling datapacks extension...");
    }

    public ComponentLogger logger() {
        return this.bootstrap.logger();
    }

    public static DatapacksExtension instance() {
        if (instance == null) throw new IllegalStateException("DatapacksExtension is not initialized");
        return instance;
    }
}
