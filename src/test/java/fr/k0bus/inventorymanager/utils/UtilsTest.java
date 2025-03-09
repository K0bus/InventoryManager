package fr.k0bus.inventorymanager.utils;

import fr.k0bus.inventorymanager.InventoryManager;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private ServerMock server;
    private InventoryManager plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testPAPIParse() {
        PlayerMock playerMock = server.addPlayer("Tester");
        assertAll(
                "Test PAPIParse w/ colors",
                () -> assertEquals("&6Test", Utils.PAPIParse("&6Test"))
        );
    }
}