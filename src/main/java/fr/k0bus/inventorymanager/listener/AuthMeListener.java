package fr.k0bus.inventorymanager.listener;

import fr.k0bus.inventorymanager.InventoryManager;
import fr.xephi.authme.events.RestoreInventoryEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuthMeListener implements Listener {

    private final InventoryManager plugin;

    public AuthMeListener(InventoryManager instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onAuthRestore(RestoreInventoryEvent e)
    {
        e.setCancelled(true);
        Player player = e.getPlayer();
        plugin.getDatabaseManager().getDao().loadInventory(
            player,
            plugin.getConfiguration().getContext(player.getWorld(), player.getGameMode())
        );
    }
}
