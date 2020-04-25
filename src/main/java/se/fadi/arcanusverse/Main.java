package se.fadi.arcanusverse;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import se.fadi.configuration.ConfigFactory;

public final class Main extends JavaPlugin {

    public static final String prefix = ChatColor.GREEN + "[ArcanusVerse]";

    private Main() {
        throw new UnsupportedOperationException();
    }

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
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
