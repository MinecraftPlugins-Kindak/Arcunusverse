package se.fadi.language;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import se.fadi.configuration.ConfigurationManager;

public class Messenger {

    public Messenger() {

    }

    public static void sendMessage(Message message, Player player) {
        FileConfiguration config = ConfigurationManager.getInstance().getLanguageConfig().getConfig();

        if (config.get(message.getMessagePath()) == null) {
            return;
        }

        String uncoloredMessage = config.getString(Message.PREFIX.getMessagePath())
                + " " + config.getString(message.getMessagePath());
        String coloredMessage = ChatColor.translateAlternateColorCodes('&', uncoloredMessage);
        player.sendMessage(coloredMessage);
    }

}
