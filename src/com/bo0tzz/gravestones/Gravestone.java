package com.bo0tzz.gravestones;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo0tzz
 */
public class Gravestone {
    private final Player player;
    private final Block location;
    private final List<ItemStack> drops;
    private final Hologram hologram;

    public Gravestone(PlayerDeathEvent event, JavaPlugin plugin) {
        this.player = event.getEntity();
        this.location = event.getEntity().getLocation().getBlock();
        drops = new ArrayList<>(event.getDrops());

        location.setType(Material.SMOOTH_BRICK);
        location.setData((byte) 3);

        if (Gravestones.doHolograms()) {
            hologram = HologramsAPI.createHologram(plugin, location.getLocation().add(0,2,0));
            hologram.appendTextLine("Here lies " + player.getDisplayName());
        } else { hologram = null; }
    }

    public void onBreak() {
        if (hologram != null) {
            hologram.delete();
        }
        for (ItemStack drop : drops) {
            location.getWorld().dropItem(location.getLocation(), drop);
        }
    }

    public Block getBlock() {
        return this.location;
    }
    public Player getPlayer() { return this.player; }

}
