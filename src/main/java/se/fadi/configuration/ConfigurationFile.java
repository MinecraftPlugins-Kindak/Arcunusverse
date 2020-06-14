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

    public void saveConfig() {
        try {
            configurationFile.save(file);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX + ChatColor.RED + " ERROR: Could not save " + file.getName() + ".");
        }
    }

    public void reloadConfig() {
        configurationFile = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return configurationFile;
    }
}
