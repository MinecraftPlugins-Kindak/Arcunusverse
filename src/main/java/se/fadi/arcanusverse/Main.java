package se.fadi.arcanusverse;

import org.bukkit.plugin.java.JavaPlugin;
import se.fadi.configuration.ConfigFactory;

public final class Main extends JavaPlugin {

    public Main() {

    }

    public static Main instance;

    public static Main getInstance() {
        return instance == null ? new Main() : instance;
    }

    @Override
    public void onEnable() {
        ConfigFactory.loadConfigurationFiles();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
