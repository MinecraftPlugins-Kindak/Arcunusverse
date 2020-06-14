package se.fadi.portal;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.World;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;

import java.util.Objects;

public class PortalReader {

    public static final String REGION_MIN_POINT_PATH = ".Portal-Location." + "Min-Point";
    public static final String REGION_MAX_POINT_PATH = ".Portal-Location." + "Max-Point";
    public static final String PORTAL_DESTINATION_PATH = ".Destination";

    private ConfigurationFile config = ConfigurationManager.getInstance().getPortalConfig();
    private final String INDICATOR = "\\|";

    private String portalName;

    public PortalReader(String portalName) {
        Objects.requireNonNull(portalName, "ERROR: Portal name cannot be null.");

        this.portalName = portalName;
    }

    public Point readMinPoint() {
        String[] minPointInfo = config.getConfig().
                getString(portalName + REGION_MIN_POINT_PATH).split(INDICATOR);
        BlockVector3 pointVector = BlockVector3.at(Double.parseDouble(minPointInfo[1]),
                Double.parseDouble(minPointInfo[2]), Double.parseDouble(minPointInfo[3]));

        return new Point(pointVector, readPortalWorld());

    }

    public Point readMaxPoint() {
        String[] maxPointInfo = config.getConfig().
                getString(portalName + REGION_MAX_POINT_PATH).split(INDICATOR);
        BlockVector3 pointVector = BlockVector3.at(Double.parseDouble(maxPointInfo[1]),
                Double.parseDouble(maxPointInfo[2]), Double.parseDouble(maxPointInfo[3]));

        return new Point(pointVector, readPortalWorld());
    }

    public World readPortalWorld() {
        String world = config.getConfig().
                getString(portalName + REGION_MIN_POINT_PATH).split(INDICATOR)[0];

        return Bukkit.getWorld(world);
    }

    public Point readDestinationPoint() {
        String[] destinationInfo = config.getConfig().
                getString(portalName + PORTAL_DESTINATION_PATH).split(INDICATOR);
        BlockVector3 pointVector = BlockVector3.at(Double.parseDouble(destinationInfo[1]),
                Double.parseDouble(destinationInfo[2]), Double.parseDouble(destinationInfo[3]));

        return new Point (pointVector, Bukkit.getWorld(destinationInfo[0]));
    }

    public Portal readPortal() {
        BukkitWorld portalWorld = new BukkitWorld(readPortalWorld());
        Region portalRegion = new CuboidRegion(portalWorld, readMinPoint().getVector(), readMaxPoint().getVector());

        return new Portal(portalName, portalRegion, readDestinationPoint());
    }

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }


}
