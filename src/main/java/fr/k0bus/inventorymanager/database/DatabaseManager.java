package fr.k0bus.inventorymanager.database;

import fr.k0bus.inventorymanager.InventoryManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;
    private final InventoryDAO dao;
    private final InventoryManager plugin;

    public DatabaseManager(InventoryManager instance)
    {
        dao = new InventoryDAO(this);
        plugin = instance;
    }

    public void init() throws SQLException, InvalidDatabaseTypeException {
        if (plugin.getConfiguration().getDBType().equals(DBType.SQLITE)) {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + plugin.getConfiguration().getSqliteFile()
            );
        } else if (plugin.getConfiguration().getDBType().equals(DBType.MYSQL)) {
            connection = DriverManager.getConnection(
                    plugin.getConfiguration().getMySQLUrl(),
                    plugin.getConfiguration().getMySQLUsername(),
                    plugin.getConfiguration().getMySQLPassword()
            );
        }
    }

    public void setupDatabase() throws SQLException{
        String createTableSQL = "CREATE TABLE IF NOT EXISTS player_inventories (" +
                "uuid VARCHAR(36) NOT NULL," +
                "inventory_context TEXT NOT NULL," +
                "inventory_data TEXT NOT NULL," +
                "PRIMARY KEY (uuid, inventory_context)" +
                ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableSQL);
    }

    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                init();
            }
        } catch (SQLException | InvalidDatabaseTypeException e) {
            plugin.getLog().log("&c" + e.getLocalizedMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                plugin.getLog().log("Database connection closed.");
            }
        } catch (SQLException e) {
            plugin.disableWithException(e);
        }
    }

    public InventoryDAO getDao() {
        return dao;
    }

    public InventoryManager getPlugin() {
        return plugin;
    }
}
