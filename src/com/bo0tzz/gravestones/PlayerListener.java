package com.bo0tzz.gravestones;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bo0tzz
 */
public class PlayerListener implements Listener {
    private final JavaPlugin plugin;

    private Map<Block, Gravestone> gravestoneMap;

    public PlayerListener(JavaPlugin plugin) {
        this.plugin = plugin;
        gravestoneMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Gravestone gravestone = new Gravestone(event, plugin);
        gravestoneMap.put(gravestone.getBlock(), gravestone);
        System.out.println(("Gravestone location: " + gravestone.getBlock()));
        event.getDrops().clear();
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Gravestone gravestone = gravestoneMap.get(event.getBlock());
        if (gravestone != null) {
            if (event.getPlayer() != gravestone.getPlayer()) {
                event.setCancelled(true);
            } else {
                gravestone.onBreak();
                gravestoneMap.remove(gravestone.getBlock());
            }
        }
    }

}
