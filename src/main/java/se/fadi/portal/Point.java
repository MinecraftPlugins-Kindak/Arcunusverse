package se.fadi.portal;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.World;

public class Point {

    private BlockVector3 vector;
    private World world;

    public Point(BlockVector3 vector, World world) {
        this.vector = vector;
        this.world = world;
    }

    public BlockVector3 getVector() {
        return vector;
    }

    public World getWorld() {
        return world;
    }

    public Location toLocation() {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    public String toString() {
        return world.getName() + "|" + vector.getX() +
                "|" + vector.getY() + "|" + vector.getZ();
    }

}
