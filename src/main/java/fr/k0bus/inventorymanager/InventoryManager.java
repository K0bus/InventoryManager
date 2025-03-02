package fr.k0bus.inventorymanager;

import fr.k0bus.inventorymanager.config.Configuration;
import fr.k0bus.inventorymanager.database.DatabaseManager;
import fr.k0bus.inventorymanager.database.InvalidDatabaseTypeException;
import fr.k0bus.inventorymanager.listener.GamemodeListener;
import fr.k0bus.inventorymanager.listener.WorldChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public final class InventoryManager extends JavaPlugin {

    private Configuration configuration;
    private DatabaseManager databaseManager;
    private static Logger logger;

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        databaseManager.close();
    }

    private void init()
    {
        try {
            logger = new Logger(this, false);
            logger.log("&bStart initialization...");
            this.configuration = new Configuration(this);
            if(configuration.isDebugMode())
            {
                logger.setDebug(true);
                logger.debug("&7> &6&lConfiguration loaded");
            }
            logger.log("&7> &6&lConfiguration loaded");
            databaseManager = new DatabaseManager(this);
            databaseManager.init();
            databaseManager.setupDatabase();
            logger.log("&7> &6&lDatabase initialized");
            getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);
            getServer().getPluginManager().registerEvents(new WorldChangeListener(this), this);
            logger.log("&7> &6&lListener registered");
            logger.log("&aInitialization ended successfully !");

        } catch (IOException | SQLException | InvalidDatabaseTypeException e)
        {
            disableWithException(e);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Logger getLog()
    {
        return logger;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void disableWithException(Exception e)
    {
        getLogger().severe("Disabling InventoryManager du to the following error : ");
        getLogger().severe(e.getLocalizedMessage());
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
