package fr.k0bus.inventorymanager.commands.main;

import fr.k0bus.inventorymanager.InventoryManager;
import fr.k0bus.inventorymanager.commands.SubCommands;
import fr.k0bus.inventorymanager.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;

public class ReloadSubCommand extends SubCommands {

    private final InventoryManager plugin;

    public ReloadSubCommand(InventoryManager instance) {
        super("reload", "im.admin.reload");
        plugin = instance;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        plugin.reloadConfig();
        Messager.send((Conversable) sender, "Configuration reloaded successfully !");
    }
}
