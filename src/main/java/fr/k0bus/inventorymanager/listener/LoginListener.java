package fr.k0bus.inventorymanager.listener;

import fr.k0bus.inventorymanager.InventoryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {

    InventoryManager plugin;

    public LoginListener(InventoryManager instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        plugin.getDatabaseManager().getDao().loadInventory(
                e.getPlayer(),
                plugin.getConfiguration().getContext(e.getPlayer().getWorld(), e.getPlayer().getGameMode())
        );
    }
}
