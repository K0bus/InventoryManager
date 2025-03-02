package fr.k0bus.inventorymanager.utils;

import org.bukkit.conversations.Conversable;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Messager {

    public static void send(Conversable target, String message)
    {
        if(target instanceof Player)
        {
            target.sendRawMessage(StringUtils.parse(message));
        }

    }
    public static void send(Conversable target, String message, HashMap<String, String> replaceMap)
    {
        for (Map.Entry<String, String> pair : replaceMap.entrySet()) {
            message = message.replace(pair.getKey(), pair.getValue());
        }
        send(target, message);
    }
}
