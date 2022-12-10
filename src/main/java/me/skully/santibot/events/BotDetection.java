package me.skully.santibot.events;

import me.skully.santibot.Main;
import me.skully.santibot.utils.InetUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class BotDetection implements Listener {

    ArrayList<Player> detected = new ArrayList<>();

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

    String lastMessage = "";

    @EventHandler
    public void onBotSend(PlayerChatEvent e){
        if(lastMessage.equals(e.getMessage())) {
            e.setCancelled(true);
            if(detected.contains(e.getPlayer())) {
            }else{
                if (Main.getInstance().getConfig().getBoolean("telegram.alert.chat-start")) {
                    InetUtils.sendTG(Main.getInstance().getConfig().getString("messages.telegram.chat-start").replace("%player%",e.getPlayer().getName()).replace("%message%", e.getMessage()));
                }
                if(detected.size() >= Main.getInstance().getConfig().getInt("chat.max-messages")) {
                    for(Player pl : detected) {
                        if (Main.getInstance().getConfig().getBoolean("telegram.alert.chat-end")) {
                            InetUtils.sendTG(Main.getInstance().getConfig().getString("messages.telegram.chat-end").replace("%player%",e.getPlayer().getName()).replace("%message%", e.getMessage()));
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("chat.command").replace("%player%",pl.getName()));
                    }
                    detected.clear();
                }else{
                    detected.add(e.getPlayer());
                }
            }
        }else{
            detected.clear();
            lastMessage = e.getMessage();
        }


    }

}
