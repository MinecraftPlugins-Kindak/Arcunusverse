package se.fadi.portal;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.World;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;

import java.util.*;

public class PortalManager {

    private ConfigurationFile config = ConfigurationManager.getInstance().getPortalConfig();
    private Set<String> portals = ConfigurationManager.getInstance().
            getPortalConfig().getConfig().getKeys(false);

    private static PortalManager portalManager;

    private PortalManager() {

    }

    public static PortalManager getInstance() {
        if (portalManager == null) {
            portalManager = new PortalManager();
        }

        return portalManager;
    }

    public boolean isPortal(String portalName) {
        Objects.requireNonNull(portalName, "ERROR: The portal name cannot be null.");

        for (String portal : portals) {
            if (portal.equals(portalName)) return true;
        }

        return false;
    }

    public boolean createPortal(String portalName, LocalSession session, World world) {
        Objects.requireNonNull(portalName, "ERROR: The portal name cannot be null.");
        Objects.requireNonNull(session, "ERROR: The player session cannot be null.");

        if (!Bukkit.getWorlds().contains(world)) {
            throw new IllegalArgumentException("ERROR: Illegal world.");
        }

        boolean success = true;

        try {
            BukkitWorld selectionWorld = new BukkitWorld(world);
            Region selection = session.getSelection(selectionWorld);
            Point destination = new Point(BlockVector3.at(world.getSpawnLocation().getX(),
                    world.getSpawnLocation().getY(), world.getSpawnLocation().getZ()), world);
            Portal portal = new Portal(portalName, selection, destination);
            portal.saveToConfig();
            portals.add(portalName);
        } catch (IncompleteRegionException e) {
            success = false;
        }

        return success;
    }

    public boolean deletePortal(String portalName) {
        Objects.requireNonNull(portalName, "ERROR: The portal name cannot be null.");

        Iterator<String> portalIter = portals.iterator();
        boolean success = false;

        while (portalIter.hasNext()) {
            String portal = portalIter.next();

            if (portal.equals(portalName)) {
                config.getConfig().set(portal, null);
                config.saveConfig();
                portals.remove(portal);
                success = true;
            }
        }
        return success;
    }

    public boolean linkPortal(String portalName, Point destination) {
        Objects.requireNonNull(portalName, "ERROR: The portal name cannot be null.");
        Objects.requireNonNull(destination, "ERROR: The destination point cannot be null");

        if (isPortal(portalName)) {
            config.getConfig().set(portalName + PortalReader.PORTAL_DESTINATION_PATH, destination.toString());
            return true;
        }

        return false;

    }

    public List<Portal> getAllPortals() {
        List<Portal> portalRegions = new ArrayList<>();
        PortalReader reader = new PortalReader("temp");

        for (String portalName : portals) {
            if (portalName != null) {
                reader.setPortalName(portalName);
                portalRegions.add(reader.readPortal());
            }
        }


        return portalRegions;
    }
}
