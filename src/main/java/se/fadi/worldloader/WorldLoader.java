package se.fadi.worldloader;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import se.fadi.arcanusverse.Main;
import se.fadi.configuration.ConfigurationManager;

import java.util.Set;

public class WorldLoader {

    private final String worldConfigSection = "Worlds";

    private final ConfigurationManager configManager;

    private static WorldLoader worldLoader;

    private WorldLoader() {
        configManager = ConfigurationManager.getInstance();
    }

    public static WorldLoader getInstance() {
        if (worldLoader == null) {
            worldLoader = new WorldLoader();
        }

        return worldLoader;
    }

    public void loadWorlds() {
        FileConfiguration worldConfig = configManager.getWorldConfig();

        Set<String> worlds = worldConfig.getConfigurationSection(worldConfigSection).getKeys(false);

        for (String world : worlds) {
            Bukkit.getServer().createWorld(new WorldCreator(world));
            Bukkit.getServer().getConsoleSender().sendMessage(Main.PREFIX +
                    " Successfully loaded world " + world);
        }


    }

    private void fixWorlds() {
        FileConfiguration worldConfig = configManager.getWorldConfig();

        Set<String> worlds = worldConfig.getConfigurationSection(worldConfigSection).getKeys(false);

        for (World world : Bukkit.getServer().getWorlds()) {

            String worldName = world.getName();

            if (!worlds.contains(world.getName())) {
                ConfigurationSection sub = worldConfig.getConfigurationSection("Worlds");
                sub.createSection(worldName);
            }
        }

    }

}
