package se.fadi.language;

import org.bukkit.configuration.file.FileConfiguration;
import se.fadi.configuration.ConfigurationFile;
import se.fadi.configuration.ConfigurationManager;

public class MessageLoader {

    private static ConfigurationFile file = ConfigurationManager.getInstance().getLanguageConfig();

    private MessageLoader() {
        throw new UnsupportedOperationException();
    }

    public static void loadMessages() {
        FileConfiguration config = file.getConfig();

        for (Message message : Message.values()) {
            if (!config.isConfigurationSection(message.getMessagePath())) {
                setDefaultConfigValue(message, config);
            }
        }
    }

    private static void setDefaultConfigValue(Message message, FileConfiguration config) {

        switch (message) {
            case PREFIX:
                config.set(message.getMessagePath(), "&E[ArcanusVerse]");
                break;
            case PORTAL_CREATION_SUCCESS:
                config.set(message.getMessagePath(), "&EThe portal has successfully been created.");
                break;
            case PORTAL_CREATION_FAILURE:
                config.set(message.getMessagePath(), "&EThe portal could not be created. Please select a region.");
                break;
            case PORTAL_CREATION_EXISTING_PORTAL:
                config.set(message.getMessagePath(), "&EThe specified portal already exists.");
                break;
            case PORTAL_CREATION_INVALID_ARGUMENTS:
                config.set(message.getMessagePath(), "&EUsage: /avcreate <portal name> <world name>");
                break;
            case PORTAL_DELETION_SUCCESS:
                config.set(message.getMessagePath(), "&EThe portal has successfully been deleted.");
                break;
            case PORTAL_DELETION_FAILURE:
                config.set(message.getMessagePath(), "&EThe portal to be deleted could not be found.");
                break;
            case PORTAL_DELETION_INVALID_ARGUMENTS:
                config.set(message.getMessagePath(), "&EUsage: /avdelete <portal name>");
                break;
            case PORTAL_LINKING_SUCCESS:
                config.set(message.getMessagePath(), "&EThe portal was successfully linked.");
                break;
            case PORTAL_LINKING_FAILURE:
                config.set(message.getMessagePath(), "&EThe portal to be linked could not be found m.");
                break;
            case PORTAL_LINKING_INVALID_ARGUMENTS:
                config.set(message.getMessagePath(), "&EUsage: /avlink <portal name>");
                break;
            case AVTELEPORT_SELF_SUCCESS:
            case AVTELEPORT_PLAYER_SUCCESS:
                config.set(message.getMessagePath(), null);
                break;
            case AVTELEPORT_SELF_FAILURE:
                config.set(message.getMessagePath(), "&EPlease specify a valid world.");
                break;
            case AVTELEPORT_PLAYER_FAILURE:
                config.set(message.getMessagePath(), "&EThe specified world or player is invalid.");
                break;
            case AVTELEPORT_INVALID_ARGUMENTS:
                config.set(message.getMessagePath(), "Usage: /avtp <world> or /avtp <player> <world>");
        }

        file.saveConfig();

    }

}
