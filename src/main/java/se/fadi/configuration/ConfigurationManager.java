package se.fadi.configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {

    private static ConfigurationManager configManager;

    private final ConfigurationFile worldConfig;

    private final List<ConfigurationFile> configurationFiles;

    private ConfigurationManager() {
        worldConfig = new ConfigurationFile("worlds.yml");
        configurationFiles = new ArrayList<>();
    }

    public static ConfigurationManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigurationManager();
        }

        return configManager;
    }

    public FileConfiguration getWorldConfig() {
        return worldConfig.getConfigurationFile();
    }

    public List<ConfigurationFile> getConfigurationFiles() {
        if (configurationFiles.isEmpty()) {
            configurationFiles.add(worldConfig);
        }

        return configurationFiles;
    }

}
