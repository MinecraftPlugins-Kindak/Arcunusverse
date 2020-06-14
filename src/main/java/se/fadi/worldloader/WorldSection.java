package se.fadi.worldloader;

import org.bukkit.configuration.ConfigurationSection;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;

import java.util.*;

public class WorldSection {

    private final int SPAWN_LIMIT_OPTIONS = 4;
    private final int SPAWN_TICKS_OPTIONS = 4;

    private static final Map<String, String> WORLD_OPTIONS;
    static {
        Map<String, String> worldOptions = new LinkedHashMap<>();

        //Spawn limits
        worldOptions.put(Option.AMBIENT_SPAWN_LIMIT.toString(), "15");
        worldOptions.put(Option.ANIMALS_SPAWN_LIMIT.toString(), "10");
        worldOptions.put(Option.WATER_ANIMALS_SPAWN_LIMIT.toString(), "15");
        worldOptions.put(Option.MONSTERS_SPAWN_LIMIT.toString(), "70");

        //Ticks per spawn
        worldOptions.put(Option.ANIMALS_SPAWN_TICKS.toString(), "400");
        worldOptions.put(Option.MONSTERS_SPAWN_TICKS.toString(), "1");
        worldOptions.put(Option.WATER_SPAWN_TICKS.toString(), "1");
        worldOptions.put(Option.AMBIENT_SPAWN_TICKS.toString(), "1");

        //General Configurations
        worldOptions.put(Option.DIFFICULTY.toString(), "Normal");
        worldOptions.put(Option.HARDCORE.toString(), "false");
        worldOptions.put(Option.PVP.toString(), "true");
        worldOptions.put(Option.THUNDERING.toString(), "true");
        worldOptions.put(Option.AUTO_SAVE.toString(), "false");

        WORLD_OPTIONS = Collections.unmodifiableMap(worldOptions);
    }

    private final ConfigurationFile file = ConfigurationManager.getInstance().getWorldConfig();

    public final static String WORLD_SECTION = "Worlds.";

    protected final String SPAWN_LIMIT_SECTION;
    protected final String SPAWN_TICKS_SECTION;
    protected final String GENERAL_SECTION;

    public WorldSection(String worldName) {
        Objects.requireNonNull(worldName, "ERROR: The world name cannot be null.");

        SPAWN_LIMIT_SECTION = WORLD_SECTION + worldName + "." + "Spawn-limits.";
        SPAWN_TICKS_SECTION = WORLD_SECTION + worldName + "." + "Ticks-per.";
        GENERAL_SECTION = WORLD_SECTION + worldName + "." + "General.";

        if(!file.getConfig().isConfigurationSection(WorldSection.WORLD_SECTION + worldName)) {
            saveToConfig();
        }

    }

    private void saveToConfig() {
        int counter = 0;

        for (String key : WORLD_OPTIONS.keySet()) {
            String value = WORLD_OPTIONS.get(key);

            if (counter < SPAWN_LIMIT_OPTIONS) {
                file.getConfig().set(SPAWN_LIMIT_SECTION + key, value);
            } else if (counter < SPAWN_LIMIT_OPTIONS + SPAWN_TICKS_OPTIONS) {
                file.getConfig().set(SPAWN_TICKS_SECTION + key, value);
            } else {
                file.getConfig().set(GENERAL_SECTION + key, value);
            }

            counter++;
        }

        file.saveConfig();
    }

    public Set<String> mergeSectionKeys() {
        Set<String> keys = new HashSet<>();

        keys.addAll(file.getConfig().getConfigurationSection(SPAWN_LIMIT_SECTION).getKeys(false));
        keys.addAll(file.getConfig().getConfigurationSection(SPAWN_TICKS_SECTION).getKeys(false));
        keys.addAll(file.getConfig().getConfigurationSection(GENERAL_SECTION).getKeys(false));

        return keys;
    }

}
