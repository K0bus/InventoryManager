package fr.k0bus.inventorymanager.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    ServerMock server;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void parse() {
        PlayerMock playerMock = server.addPlayer("Tester");
        assertEquals("§6Test", StringUtils.parse("&6Test", playerMock));
    }

    @Test
    void translateColor() {
        assertEquals("§l§x§f§f§f§f§f§fTest", StringUtils.translateColor("&l&#ffffffTest"));
    }

    @Test
    void proper() {
        assertEquals("This Is A Simple Text", StringUtils.proper("This_is_a_simple_text"));
    }
}