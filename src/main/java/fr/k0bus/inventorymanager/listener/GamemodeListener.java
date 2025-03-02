package fr.k0bus.inventorymanager.listener;

import fr.k0bus.inventorymanager.InventoryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeListener implements Listener {

    private final InventoryManager plugin;

    public GamemodeListener(InventoryManager plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGMChange(PlayerGameModeChangeEvent e)
    {
        plugin.getDatabaseManager().getDao().switchInventory(
                e.getPlayer(),
                plugin.getConfiguration().getContext(e.getPlayer().getWorld(), e.getPlayer().getGameMode()),
                plugin.getConfiguration().getContext(e.getPlayer().getWorld(), e.getNewGameMode())
        );
    }
}
