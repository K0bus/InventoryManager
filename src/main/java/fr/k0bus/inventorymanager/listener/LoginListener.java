package fr.k0bus.inventorymanager.listener;

import fr.k0bus.inventorymanager.InventoryManager;
import fr.xephi.authme.AuthMe;
import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.settings.properties.AuthMeSettingsRetriever;
import fr.xephi.authme.settings.properties.RestrictionSettings;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
