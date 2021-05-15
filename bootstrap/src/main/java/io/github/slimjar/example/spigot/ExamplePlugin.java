package io.github.slimjar.example.spigot;

import io.github.slimjar.app.Application;
import io.github.slimjar.app.ApplicationConfiguration;
import io.github.slimjar.app.ApplicationFactory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExamplePlugin extends JavaPlugin {
    private Application app = null;

    @Override
    public void onLoad() {
        final Logger logger = getLogger();

        logger.log(Level.INFO, "Loading dependencies of ExamplePlugin...");
        try {
            final ApplicationConfiguration config = ApplicationConfiguration.createDefault("example-plugin");
            final ApplicationFactory appFactory = new ApplicationFactory(config);
            final Collection<String> modules = Collections.singleton("plugin");
            app = appFactory.createIsolatedApplication(modules, "io.github.slimjar.example.spigot.PluginApplication", this.getClassLoader(), this);
            getLogger().log(Level.INFO, "Successfully loaded of ExamplePlugin...");
        } catch (ReflectiveOperationException | IOException exception) {
            getLogger().log(Level.SEVERE, "Failed to load application dependencies!");
            exception.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        if (app == null) {
            this.getServer().getScheduler().runTaskLater(this, () -> {
               this.getServer().getPluginManager().disablePlugin(this);
            }, 100L);
            return;
        }
        app.start();
    }

    @Override
    public void onDisable() {
        if (app != null) {
            app.stop();
        }
    }
}
