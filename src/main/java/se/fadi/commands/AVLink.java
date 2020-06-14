package se.fadi.commands;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.fadi.language.Message;
import se.fadi.language.Messenger;
import se.fadi.portal.Point;
import se.fadi.portal.PortalManager;;

public class AVLink implements CommandExecutor {

    private PortalManager portalManager = PortalManager.getInstance();

    public AVLink() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 1) {
            Location destination = player.getLocation();
            boolean linked = portalManager.linkPortal(args[0], new Point(BlockVector3.at
                    (destination.getX(), destination.getY(), destination.getZ()), player.getWorld()));
            sendLinkingMessage(linked, player);
        } else {
            Messenger.sendMessage(Message.PORTAL_LINKING_INVALID_ARGUMENTS, player);
        }

        return true;
    }

    private void sendLinkingMessage(boolean success, Player player) {
        if (success) {
            Messenger.sendMessage(Message.PORTAL_LINKING_SUCCESS, player);
        } else {
            Messenger.sendMessage(Message.PORTAL_LINKING_FAILURE, player);
        }
    }
}
