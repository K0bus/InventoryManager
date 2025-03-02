package fr.k0bus.inventorymanager.database;

import fr.k0bus.inventorymanager.InventoryManager;
import fr.k0bus.inventorymanager.utils.InventoryUtils;
import fr.k0bus.inventorymanager.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class InventoryDAO {

    private final DatabaseManager databaseManager;

    public InventoryDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void saveInventory(Player player, String context) {
        String sql = "REPLACE INTO player_inventories (uuid, inventory_context, inventory_data) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = this.databaseManager.getConnection().prepareStatement(sql);
            String inventoryData = InventoryUtils.serializeInventory(player.getInventory().getContents());

            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, context);
            statement.setString(3, inventoryData);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            InventoryManager.getStaticLogger().log(e.getMessage());
        }
    }

    public void loadInventory(Player player, String context) {
        String sql = "SELECT inventory_data FROM player_inventories WHERE uuid = ? AND inventory_context = ?";

        try {
            PreparedStatement statement = this.databaseManager.getConnection().prepareStatement(sql);
            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, context);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String inventoryData = resultSet.getString("inventory_data");
                ItemStack[] items = InventoryUtils.deserializeInventory(inventoryData);
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("InventoryManager"), () -> {
                    player.getInventory().setContents(items);
                });
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            InventoryManager.getStaticLogger().log(e.getMessage());
        }
    }

    public void switchInventory(Player player, String oldContext, String newContext)
    {
        if(oldContext.equals(newContext)) return;
        databaseManager.getPlugin().getLog().debug(
                "Inventory of player " + player.getDisplayName() + " switched from " + oldContext + " to " + newContext
        );

        HashMap<String, String> replaceMap = new HashMap<>();
        replaceMap.put("%1", oldContext);
        replaceMap.put("%2", newContext);
        Messager.send(
                player,
                "&7[&6&lInventoryManager&r&7] &bInventory switched from &a%1 &bto &a%2",
                replaceMap
        );
        try{
            saveInventory(player, oldContext);
        }finally {
            loadInventory(player, newContext);
        }
    }
}