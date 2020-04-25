package se.fadi.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.fadi.arcanusverse.Main;

import java.io.File;
import java.io.IOException;

public abstract class ConfigManager {

    private final Main plugin;

    public ConfigManager() {
        plugin = Main.getInstance();
    }

    public abstract void loadConfig();

    protected void loadConfig(File file, FileConfiguration config) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), "worlds.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.RED + " ERROR: Could not create worlds.yml.");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    protected void saveConfig(File file, FileConfiguration config) {
        try {
            config.save(file);
            Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.GREEN + " The config " + file.getName() + " has been successfully saved.");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.RED + " ERROR: Could not save " + file.getName() + ".");
        }
    }

    protected void reloadConfig(File file, FileConfiguration config) {
        config = YamlConfiguration.loadConfiguration(file);
        Bukkit.getServer().getConsoleSender().sendMessage(Main.prefix + ChatColor.GREEN + " The config " + file.getName() + " has been reloaded.");
    }
}
