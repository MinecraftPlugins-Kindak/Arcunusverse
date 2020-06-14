package se.fadi.worldloader;

public enum Option {
    AMBIENT_SPAWN_LIMIT("Ambient_spawn_limit"),
    ANIMALS_SPAWN_LIMIT("Animals_spawn_limit"),
    WATER_ANIMALS_SPAWN_LIMIT("Water_animals_spawn_limit"),
    MONSTERS_SPAWN_LIMIT("Monsters_spawn_limit"),
    ANIMALS_SPAWN_TICKS("Animals_spawn_ticks"),
    MONSTERS_SPAWN_TICKS("Monsters_spawn_ticks"),
    WATER_SPAWN_TICKS("Water_spawn_ticks"),
    AMBIENT_SPAWN_TICKS("Ambient_spawn_ticks"),
    DIFFICULTY("Difficulty"),
    HARDCORE("Hardcore"),
    PVP("PvP"),
    THUNDERING("Thundering"),
    AUTO_SAVE("Auto_save");

    private final String option;

    Option(String option) {
        this.option = option;
    }

    public String toString() {
        return option;
    }
}
