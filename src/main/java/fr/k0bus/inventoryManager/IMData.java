package fr.k0bus.inventoryManager;

import dev.dejvokep.boostedyaml.block.implementation.Section;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class IMData {

    private final InventoryManager plugin;

    private final HashMap<UUID, String> playerInventoryMap = new HashMap<>();

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public IMData(InventoryManager instance)
    {
        this.plugin = instance;
    }

    public void playerLogin(Player player)
    {
        playerInventoryMap.put(
                player.getUniqueId(),
                getInventory(player.getLocation().getWorld(), player.getGameMode())
        );
    }
    public void playerLogout(OfflinePlayer player)
    {
        playerInventoryMap.remove(player.getUniqueId());
    }
    public void cleanCache()
    {
        playerInventoryMap.keySet().forEach((uuid) -> {
            if(Bukkit.getServer().getPlayer(uuid) == null)
            {
                playerInventoryMap.remove(uuid);
            }
        });
    }

    public boolean isPlayerLoggedIn(OfflinePlayer player)
    {
        return playerInventoryMap.containsKey(player.getUniqueId());
    }
    public String getInventory(World world, GameMode gameMode)
    {
        Section configurationSection = this.plugin.getSettings().getSection("inventories");
        String inventoryName = "default";
        for (Object key : configurationSection.getKeys()) {
            if (configurationSection.getStringList(key + ".world")
                    .stream().noneMatch(world.getName()::equalsIgnoreCase)) continue;
            if (configurationSection.getStringList(key + ".gamemode")
                    .stream().noneMatch(gameMode.name()::equalsIgnoreCase)) continue;
            inventoryName = key.toString();
        }
        return inventoryName;
    }

    public void loadInventory(Player player, World world, GameMode gameMode)
    {

    }

}
