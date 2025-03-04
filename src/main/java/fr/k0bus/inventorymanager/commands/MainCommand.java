package fr.k0bus.inventorymanager.commands;

import fr.k0bus.inventorymanager.InventoryManager;
import fr.k0bus.inventorymanager.commands.main.ReloadSubCommand;
import fr.k0bus.inventorymanager.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;

public class MainCommand extends Command {

    private final InventoryManager plugin;

    public MainCommand(InventoryManager instance) {
        super("im", "im.admin");
        plugin = instance;
        addSubCommands(new ReloadSubCommand(getPlugin()));
        setUsage("/im <argument>");
        setDescription("All command added to InventoryManager");
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        Messager.send((Conversable) sender, "InventoryManager loaded in the server !");
    }

    public InventoryManager getPlugin() {
        return plugin;
    }
}
