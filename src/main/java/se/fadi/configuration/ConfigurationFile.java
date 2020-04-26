package se.fadi.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.fadi.arcanusverse.Main;
import java.io.File;
import java.io.IOException;

public class ConfigurationFile {

    private final Main plugin;

    private FileConfiguration configurationFile;
    private File file;
    private final String fileName;

    public ConfigurationFile(String fileName) {
        plugin = Main.getInstance();
        this.fileName = fileName;
    }

    public void loadConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX + ChatColor.RED + " ERROR: Could not create worlds.yml.");
            }
        }

        configurationFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(File file, FileConfiguration config) {
        try {
            config.save(file);
            Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX + ChatColor.GREEN + " The config " + file.getName() + " has been successfully saved.");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX + ChatColor.RED + " ERROR: Could not save " + file.getName() + ".");
        }
    }

    public void reloadConfig() {
        configurationFile = YamlConfiguration.loadConfiguration(file);
        Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX + ChatColor.GREEN + " The config " + fileName + " has been reloaded.");

    }

    public FileConfiguration getConfigurationFile() {
        return configurationFile;
    }
}
