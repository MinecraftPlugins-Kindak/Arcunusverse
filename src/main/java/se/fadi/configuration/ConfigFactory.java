package se.fadi.configuration;

import java.util.ArrayList;
import java.util.List;

public final class ConfigFactory {

    private ConfigFactory() {
        throw new UnsupportedOperationException();
    }

    public static void loadConfigurationFiles() {

        for (ConfigManager manager : getConfigurationFiles()) {
            manager.loadConfig();
        }
    }

    private static List<ConfigManager> getConfigurationFiles() {
        List<ConfigManager> configurationFiles = new ArrayList<>();

        configurationFiles.add(new WorldConfigManager());

        return configurationFiles;
    }
}
