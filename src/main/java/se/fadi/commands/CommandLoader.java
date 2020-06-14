package se.fadi.commands;

import se.fadi.arcanusverse.Main;

public class CommandLoader {

    private static Main plugin = Main.getInstance();

    private CommandLoader() {
        throw new UnsupportedOperationException();
    }

    public static void loadCommands() {
        plugin.getCommand("AVTeleport").setExecutor(new AVTeleport());
        plugin.getCommand("AVCreate").setExecutor(new AVCreate());
        plugin.getCommand("AVDelete").setExecutor(new AVDelete());
        plugin.getCommand("AVLink").setExecutor(new AVLink());
    }
}
