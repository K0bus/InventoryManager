package fr.k0bus.inventoryManager;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class InventoryManager extends JavaPlugin {

    private IMData data;
    private YamlDocument settings;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("Starting InventoryManager (" + this.getDescription().getVersion() + ")");
        this.data = new IMData(this);
        try {
            this.settings = YamlDocument.create(
                    new File(getDataFolder(), "config.yml"),
                    Objects.requireNonNull(getResource("config.yml"))
            );
            this.settings.setSettings(
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build()
            );
            this.settings.update();
        } catch (IOException e) {
            this.getLogger().severe("Can't write file config.yml. Plugins stop.");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        this.getServer().getPluginManager().registerEvents(new IMEvents(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public IMData getData() {
        return data;
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    public YamlDocument getSettings() {
        return settings;
    }
}
