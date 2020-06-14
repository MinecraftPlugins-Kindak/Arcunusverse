package se.fadi.configuration;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {

    private static ConfigurationManager configManager;

    private final ConfigurationFile worldConfig;
    private final ConfigurationFile portalConfig;
    private final ConfigurationFile languageConfig;

    private final List<ConfigurationFile> configurationFiles;

    private ConfigurationManager() {
        worldConfig = new ConfigurationFile("worlds.yml");
        portalConfig = new ConfigurationFile("portals.yml");
        languageConfig = new ConfigurationFile("language.yml");
        configurationFiles = new ArrayList<>();
    }

    public static ConfigurationManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigurationManager();
        }

        return configManager;
    }

    public ConfigurationFile getWorldConfig() {
        return worldConfig;
    }

    public ConfigurationFile getPortalConfig() {
        return portalConfig;
    }

    public ConfigurationFile getLanguageConfig() {
        return languageConfig;
    }

    public List<ConfigurationFile> getConfigurationFiles() {
        if (configurationFiles.isEmpty()) {
            configurationFiles.add(worldConfig);
            configurationFiles.add(portalConfig);
            configurationFiles.add(languageConfig);
        }

        return configurationFiles;
    }

}
