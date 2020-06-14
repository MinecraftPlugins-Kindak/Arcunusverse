package se.fadi.commands;

import com.sk89q.worldedit.LocalSession;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.fadi.arcanusverse.WorldEdit;
import se.fadi.language.Message;
import se.fadi.language.Messenger;
import se.fadi.portal.PortalManager;

public class AVCreate implements CommandExecutor {

    private PortalManager portalManager = PortalManager.getInstance();

    public AVCreate() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 2) {
            LocalSession session = WorldEdit.getPlugin().getSession(player);

            if (!portalManager.isPortal(args[0])) {
                boolean created = portalManager.createPortal(args[0], session, Bukkit.getWorld(args[1]));
                sendCreationMessage(created, player);
            } else {
                Messenger.sendMessage(Message.PORTAL_CREATION_EXISTING_PORTAL, player);
            }
        } else {
            Messenger.sendMessage(Message.PORTAL_CREATION_INVALID_ARGUMENTS, player);
        }

        return false;
    }

    private void sendCreationMessage(boolean success, Player player) {
        if (success) {
            Messenger.sendMessage(Message.PORTAL_CREATION_SUCCESS, player);
        } else {
            Messenger.sendMessage(Message.PORTAL_CREATION_FAILURE, player);
        }
    }

}
