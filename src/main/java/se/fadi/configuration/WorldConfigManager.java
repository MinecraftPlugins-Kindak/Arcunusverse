package se.fadi.configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class WorldConfigManager extends ConfigManager {

    /**Files and configurations*/
    private FileConfiguration worldConfig;
    private File worlds;

    public WorldConfigManager() {

    }

    @Override
    public void loadConfig() {
        super.loadConfig(worlds, worldConfig);
    }

    public void saveConfig() {
        super.saveConfig(worlds, worldConfig);
    }

    public void reloadConfig() {
       super.reloadConfig(worlds, worldConfig);
    }

    public FileConfiguration getConfig() {
        return worldConfig;
    }
}
