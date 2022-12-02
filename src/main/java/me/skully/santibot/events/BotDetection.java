package me.skully.santibot.events;

import me.skully.santibot.Main;
import me.skully.santibot.utils.InetUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BotDetection implements Listener {

    @EventHandler
    public void onBotJoin(PlayerJoinEvent e){
        for(String str : Main.getInstance().getConfig().getStringList("bots.nicks")) {
            if (e.getPlayer().getName().contains(str)) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("bots.command").replace("%player%", e.getPlayer().getName()));
                if (Main.getInstance().getConfig().getBoolean("telegram.alert.name")) {
                    InetUtils.sendTG(Main.getInstance().getConfig().getString("messages.telegram.bot").replace("%bot%",str).replace("%nick%", e.getPlayer().getName()));
                }
            }
        }
    }

}
