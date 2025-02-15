package fr.k0bus.inventoryManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class IMEvents implements Listener {

    InventoryManager plugin;

    public IMEvents(InventoryManager instance)
    {
        this.plugin = instance;
    }


    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent e)
    {
        this.plugin.getData().loadInventory(
                e.getPlayer(),
                e.getPlayer().getWorld(),
                e.getNewGameMode()
        );
    }
    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e)
    {
        this.plugin.getData().loadInventory(
                e.getPlayer(),
                e.getTo().getWorld(),
                e.getPlayer().getGameMode()
        );
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e)
    {
        this.plugin.getData().playerLogin(e.getPlayer());
    }
    @EventHandler
    public void onLogout(PlayerQuitEvent e)
    {
        this.plugin.getData().playerLogout(e.getPlayer());
    }

}
