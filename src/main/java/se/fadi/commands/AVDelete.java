package se.fadi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.fadi.language.Message;
import se.fadi.language.Messenger;
import se.fadi.portal.PortalManager;

public class AVDelete implements CommandExecutor {

    private PortalManager portalManager = PortalManager.getInstance();

    public AVDelete() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 1) {
            boolean portalDeleted = portalManager.deletePortal(args[0]);
            sendDeletionMessage(portalDeleted, player);
        } else {
            Messenger.sendMessage(Message.PORTAL_DELETION_INVALID_ARGUMENTS, player);
        }

        return true;
    }

    private void sendDeletionMessage(boolean success, Player player) {
        if (success) {
            Messenger.sendMessage(Message.PORTAL_DELETION_SUCCESS, player);
        } else {
            Messenger.sendMessage(Message.PORTAL_DELETION_FAILURE, player);
        }
    }
}
