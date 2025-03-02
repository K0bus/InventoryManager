package fr.k0bus.inventorymanager.utils;

import fr.k0bus.inventorymanager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class InventoryUtils {

    public static String serializeInventory(ItemStack[] inventory) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeInt(inventory.length);
            for (ItemStack item : inventory) {
                dataOutput.writeObject(item);
            }
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ItemStack[] deserializeInventory(String data) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
             BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
            int size = dataInput.readInt();
            ItemStack[] inventory = new ItemStack[size];
            for (int i = 0; i < size; i++) {
                inventory[i] = (ItemStack) dataInput.readObject();
            }
            return inventory;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ItemStack[0];
        }
    }
}
