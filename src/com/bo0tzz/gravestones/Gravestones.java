package com.bo0tzz.gravestones;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * Created by bo0tzz
 */
public class Gravestones extends JavaPlugin {

    private static boolean hologramEnabled;

    @Override
    public void onEnable() {
        hologramEnabled = this.getServer().getPluginManager().isPluginEnabled("HolographicDisplays");
        if (hologramEnabled) {
            Collection<Hologram> holograms = HologramsAPI.getHolograms(this);
            for (Hologram hologram : holograms) {
                hologram.delete();
            }
        }
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    public static boolean doHolograms() {
        return hologramEnabled;
    }

}
