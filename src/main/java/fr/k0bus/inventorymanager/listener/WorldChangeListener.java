package fr.k0bus.inventorymanager.listener;

import fr.k0bus.inventorymanager.InventoryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    InventoryManager plugin;

    public WorldChangeListener(InventoryManager instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e)
    {
        plugin.getDatabaseManager().getDao().switchInventory(
                e.getPlayer(),
                plugin.getConfiguration().getContext(e.getFrom(), e.getPlayer().getGameMode()),
                plugin.getConfiguration().getContext(e.getPlayer().getWorld(), e.getPlayer().getGameMode())
        );
    }
}
