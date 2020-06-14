package se.fadi.portal;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.util.List;

public class PortalJoinEvent implements Listener {

    private PortalManager manager = PortalManager.getInstance();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        List<Portal> portals = manager.getAllPortals();

        BlockVector3 to = BlockVector3.at(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ());

        for (Portal portal : portals) {
            Region portalRegion = portal.getPortalRegion();

            if (portalRegion.contains(to)) {
                event.getPlayer().teleport(portal.getDestinationPoint().toLocation());
            }
        }

    }
}
