package io.github.slimjar.example.spigot;

import io.github.slimjar.app.Application;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class PluginApplication extends Application {
    private final JavaPlugin plugin;

    public PluginApplication(final ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean start() {
        plugin.getLogger().log(Level.INFO, "Started application!");
        plugin.getServer().getPluginManager().registerEvents(new ChatListener(), plugin);
        return true;
    }

    @Override
    public boolean stop() {
        plugin.getLogger().log(Level.INFO, "Stopping application!");
        return true;
    }
}
