package me.skully.santibot;

import me.skully.santibot.commands.SAntiBotCommand;
import me.skully.santibot.events.BotDetection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Bukkit.getPluginCommand("santibot").setExecutor(new SAntiBotCommand());

        Bukkit.getPluginManager().registerEvents(new BotDetection(),this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }


    public static Main getInstance() {
        return instance;
    }
}
