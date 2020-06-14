package se.fadi.arcanusverse;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import se.fadi.commands.CommandLoader;
import se.fadi.configuration.ConfigurationLoader;
import se.fadi.language.MessageLoader;
import se.fadi.portal.PortalJoinEvent;
import se.fadi.worldloader.WorldLoader;

public final class Main extends JavaPlugin {

    public static final String PREFIX = ChatColor.GREEN + "[ArcanusVerse]";

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

    @Override
    public void onEnable() {
        ConfigurationLoader.loadConfigurationFiles();
        WorldLoader.loadWorlds();
        CommandLoader.loadCommands();
        MessageLoader.loadMessages();
        this.getServer().getPluginManager().registerEvents(new PortalJoinEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
