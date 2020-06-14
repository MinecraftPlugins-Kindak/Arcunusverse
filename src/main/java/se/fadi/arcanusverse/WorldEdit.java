package se.fadi.arcanusverse;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class WorldEdit {

    private WorldEdit() {
        throw new UnsupportedOperationException();
    }

    public static WorldEditPlugin getPlugin() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        if (plugin instanceof WorldEditPlugin) {
            return (WorldEditPlugin) plugin;
        }

        return null;
    }

}
