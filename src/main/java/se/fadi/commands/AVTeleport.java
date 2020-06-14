package se.fadi.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.fadi.language.Message;
import se.fadi.language.Messenger;

import java.io.File;

public class AVTeleport implements CommandExecutor {

    public AVTeleport() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        int argumentLength = args.length;

        switch (argumentLength) {
            case 1:
                boolean teleported = teleport(player, args[0]);

                if (teleported) {
                    Messenger.sendMessage(Message.AVTELEPORT_SELF_SUCCESS, player);
                } else {
                    Messenger.sendMessage(Message.AVTELEPORT_SELF_FAILURE, player);
                }
                break;
            case 2:
                if (!isOnlinePlayer(args[0])) {
                    Messenger.sendMessage(Message.AVTELEPORT_PLAYER_FAILURE, player);
                }

                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                boolean playerTeleported = teleport(targetPlayer, args[1]);

                if(playerTeleported) {
                    Messenger.sendMessage(Message.AVTELEPORT_PLAYER_SUCCESS, player);
                } else {
                    Messenger.sendMessage(Message.AVTELEPORT_PLAYER_FAILURE, player);
                }
                break;
            default:
                Messenger.sendMessage(Message.AVTELEPORT_INVALID_ARGUMENTS, player);
        }

        return true;
    }

    private boolean teleport(Player player, String argument) {
        boolean validWorld = false;

        for (File worldFile : Bukkit.getWorldContainer().listFiles()) {

            String worldName = worldFile.getName();

            if (argument.equalsIgnoreCase(worldName)) {
                World world = Bukkit.getServer().getWorld(worldName);
                Location location = new Location(world, world.getSpawnLocation().getX(),
                        world.getSpawnLocation().getY(), world.getSpawnLocation().getZ());
                validWorld = true;

                player.teleport(location);
            }
        }

        return validWorld;
    }

    private boolean isOnlinePlayer(String playerName) {
        return Bukkit.getServer().getPlayerExact(playerName) != null;
    }

}
