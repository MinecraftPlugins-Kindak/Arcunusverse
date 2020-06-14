package se.fadi.portal;

import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;
import java.util.Objects;


public class Portal {

    private ConfigurationFile config = ConfigurationManager.getInstance().getPortalConfig();

    private String name;
    private Region portalRegion;
    private Point destination;

    public Portal(String name, Region portalLocation, Point destination) {
        Objects.requireNonNull(name, "ERROR: The portal name cannot be null.");
        Objects.requireNonNull(portalLocation, "ERROR: The portal position cannot be null.");
        Objects.requireNonNull(destination, "ERROR: The portal destination cannot be null.");

        this.name = name;
        this.portalRegion = portalLocation;
        this.destination = destination;
    }

    public void saveToConfig() {
        Point minPortalPoint = new Point(portalRegion.getMinimumPoint(), Bukkit.getWorld(portalRegion.getWorld().getName()));
        Point maxPortalPoint = new Point(portalRegion.getMaximumPoint(), Bukkit.getWorld(portalRegion.getWorld().getName()));

        config.getConfig().set(name + PortalReader.REGION_MIN_POINT_PATH, minPortalPoint.toString());
        config.getConfig().set(name + PortalReader.REGION_MAX_POINT_PATH, maxPortalPoint.toString());
        config.getConfig().set(name + PortalReader.PORTAL_DESTINATION_PATH, destination.toString());

        config.saveConfig();
    }

    public void updateDestinationPoint(Point location) {
        this.destination = location;
    }

    public void updatePortalLocation(Region region) {
        this.portalRegion = region;
    }

    public String getName() {
        return name;
    }

    public Region getPortalRegion() {
        return portalRegion;
    }

    public Point getDestinationPoint() {
        return destination;
    }

}
