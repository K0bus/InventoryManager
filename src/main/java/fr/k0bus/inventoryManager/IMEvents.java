package fr.k0bus.inventoryManager;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class IMEvents implements Listener {

    InventoryManager plugin;

    public IMEvents(InventoryManager instance)
    {
        this.plugin = instance;
    }


    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e)
    {

    }
    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e)
    {

    }

    public void change(Player player, World world, GameMode gameMode)
    {
        String inventory = "default";

    }
}
