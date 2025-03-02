package fr.k0bus.inventorymanager.config;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer;
import fr.k0bus.inventorymanager.InventoryManager;
import fr.k0bus.inventorymanager.database.DBType;
import fr.k0bus.inventorymanager.database.InvalidDatabaseTypeException;
import org.bukkit.GameMode;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configuration extends YamlDocument {

    public Configuration(InventoryManager plugin) throws IOException {
        super(
                new File(plugin.getDataFolder(), "config.yml"),
                plugin.getResource("config.yml"),
                GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).build()
        );
    }

    public boolean isDebugMode()
    {
        return getBoolean("debug-log");
    }

    public DBType getDBType() throws InvalidDatabaseTypeException
    {
        return DBType.getFromID(getString("database.type"));
    }

    public String getSqliteFile()
    {
        return getString("database.sqlite.file");
    }

    private String getMySQLHost()
    {
        return getString("database.mysql.host");
    }
    public int getMySQLPort()
    {
        return getInt("database.mysql.port");
    }
    public String getMySQLDatabase()
    {
        return getString("database.mysql.database");
    }
    public String getMySQLUsername()
    {
        return getString("database.mysql.username");
    }
    public String getMySQLPassword()
    {
        return getString("database.mysql.password");
    }

    public String getMySQLUrl()
    {
        return "jdbc:mysql://" + getMySQLHost() + ":" + getMySQLPort() + "/" + getMySQLDatabase();
    }
    public String getDefaultContext()
    {
        return getString("default_contexts");
    }
    public List<Map<?, ?>> getContexts() {
        List<?> rawContexts = getList("contexts", new ArrayList<>());

        List<Map<?, ?>> contexts = new ArrayList<>();
        for (Object obj : rawContexts) {
            if (obj instanceof Map) {
                contexts.add((Map<?, ?>) obj);
            }
        }

        return contexts;
    }
    public String getContext(World world, GameMode gameMode) {
        for (Map<?, ?> contextMap : getContexts()) {
            Object worldsObj = contextMap.get("worlds");
            Object gamemodesObj = contextMap.get("gamemodes");
            Object inventoryObj = contextMap.get("inventory");

            if (
                    worldsObj instanceof List<?> rawWorlds &&
                    gamemodesObj instanceof List<?> rawGamemodes &&
                    inventoryObj instanceof String inventoryName
            ) {
                List<String> worlds = rawWorlds.stream()
                        .filter(o -> o instanceof String)
                        .map(o -> (String) o)
                        .toList();

                List<String> gamemodes = rawGamemodes.stream()
                        .filter(o -> o instanceof String)
                        .map(o -> (String) o)
                        .toList();

                boolean worldMatches = worlds.contains(world.getName());
                boolean gamemodeMatches = gamemodes.contains(gameMode.name());

                if (worldMatches && gamemodeMatches) {
                    return inventoryName;
                }
            }
        }
        return getDefaultContext();
    }
}
