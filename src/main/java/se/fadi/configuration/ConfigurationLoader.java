package se.fadi.configuration;

import java.util.List;

public final class ConfigurationLoader {

    private ConfigurationLoader() {
        throw new UnsupportedOperationException();
    }

    public static void loadConfigurationFiles() {
        List<ConfigurationFile> configurationFiles = ConfigurationManager.getInstance().getConfigurationFiles();

        for (ConfigurationFile file : configurationFiles) {
            file.loadConfig();
        }
    }
}
