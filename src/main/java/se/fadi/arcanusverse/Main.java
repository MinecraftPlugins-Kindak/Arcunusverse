package se.fadi.arcanusverse;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import se.fadi.configuration.ConfigurationLoader;
import se.fadi.worldloader.WorldLoader;

public final class Main extends JavaPlugin {

    public static final String PREFIX = ChatColor.GREEN + "[ArcanusVerse]";

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

    @Override
    public void onEnable() {
        ConfigurationLoader.loadConfigurationFiles();
        WorldLoader.getInstance().loadWorlds();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
