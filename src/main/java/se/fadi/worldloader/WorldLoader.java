package se.fadi.worldloader;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import se.fadi.arcanusverse.Main;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;
import java.io.File;

public class WorldLoader {

    private static ConfigurationFile file = ConfigurationManager.getInstance().getWorldConfig();

    private WorldLoader() {
        throw new UnsupportedOperationException();
    }

    public static void loadWorlds() {

        for (File world : Bukkit.getWorldContainer().listFiles()) {

            String worldName = world.getName();
            Bukkit.getServer().createWorld(new WorldCreator(worldName));
            WorldSection section = new WorldSection(worldName);

            boolean loaded = loadWorldProperties(worldName, section);

            if (loaded) {
                Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX +
                        " Successfully loaded world " + world);
            } else {
                Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX +
                        " Unable to load the world properties of " + world);
            }

        }

    }

    private static boolean loadWorldProperties(String worldName, WorldSection section) {
        FileConfiguration config = file.getConfig();

        boolean loaded = true;

        for (String key : section.mergeSectionKeys()) {
            Option option = Option.valueOf(key.toUpperCase());

            World world = Bukkit.getWorld(worldName);

            try {
                switch (option) {
                    case AMBIENT_SPAWN_LIMIT:
                        int ambientLimit = config.getConfigurationSection(section.SPAWN_LIMIT_SECTION).getInt(key);
                        world.setAmbientSpawnLimit(ambientLimit);
                        break;
                    case ANIMALS_SPAWN_LIMIT:
                        int animalLimit = config.getConfigurationSection(section.SPAWN_LIMIT_SECTION).getInt(key);
                        world.setAnimalSpawnLimit(animalLimit);
                        break;
                    case WATER_ANIMALS_SPAWN_LIMIT:
                        int waterAnimalLimit = config.getConfigurationSection(section.SPAWN_LIMIT_SECTION).getInt(key);
                        world.setWaterAnimalSpawnLimit(waterAnimalLimit);
                        break;
                    case MONSTERS_SPAWN_LIMIT:
                        int monsterLimit = config.getConfigurationSection(section.SPAWN_LIMIT_SECTION).getInt(key);
                        world.setMonsterSpawnLimit(monsterLimit);
                        break;
                    case ANIMALS_SPAWN_TICKS:
                        int animalSpawnTicks = config.getConfigurationSection(section.SPAWN_TICKS_SECTION).getInt(key);
                        world.setTicksPerAnimalSpawns(animalSpawnTicks);
                        break;
                    case AMBIENT_SPAWN_TICKS:
                        int ambientSpawnTicks = config.getConfigurationSection(section.SPAWN_TICKS_SECTION).getInt(key);
                        world.setTicksPerAmbientSpawns(ambientSpawnTicks);
                        break;
                    case WATER_SPAWN_TICKS:
                        int waterSpawnTicks = config.getConfigurationSection(section.SPAWN_TICKS_SECTION).getInt(key);
                        world.setTicksPerWaterSpawns(waterSpawnTicks);
                        break;
                    case MONSTERS_SPAWN_TICKS:
                        int monsterSpawnTicks = config.getConfigurationSection(section.SPAWN_TICKS_SECTION).getInt(key);
                        world.setTicksPerMonsterSpawns(monsterSpawnTicks);
                        break;
                    case PVP:
                        boolean pvp = config.getConfigurationSection(section.GENERAL_SECTION).getBoolean(key);
                        world.setPVP(pvp);
                        break;
                    case HARDCORE:
                        boolean hardcore = config.getConfigurationSection(section.GENERAL_SECTION).getBoolean(key);
                        world.setHardcore(hardcore);
                        break;
                    case AUTO_SAVE:
                        boolean autoSave = config.getConfigurationSection(section.GENERAL_SECTION).getBoolean(key);
                        world.setAutoSave(autoSave);
                        break;
                    case DIFFICULTY:
                        Difficulty difficulty = Difficulty.valueOf(config.getConfigurationSection(section.GENERAL_SECTION).getString(key).toUpperCase());
                        world.setDifficulty(difficulty);
                        break;
                }
            } catch (NullPointerException e) {
                System.err.println("ERROR: World configuration options missing or invalid.");
                loaded = false;
            }

            file.saveConfig();

        }

        return loaded;
    }

}
