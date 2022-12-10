package me.skully.santibot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.skully.santibot.Main;

public class SAntiBotCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("santibot.use")) {
            return true;
        }

        if(args.length == 0) {
            sender.sendMessage("§bSAntiBot §7| §f/santibot §areload §f- перезагружает конфигурацию плагина");
            sender.sendMessage("§bSAntiBot §7| §f/santibot §aadd §7НИКНЕЙМ §f- добавляет никнейм в конфигурацию");
        }else if(args[0].equalsIgnoreCase("reload")) {
            Main.getInstance().reloadConfig();
            sender.sendMessage("§bSAntiBot §7| §fПлагин был §aуспешно §fперезапущен!");
            return true;
        }else if(args[0].equalsIgnoreCase("add")){
            Main.getInstance().getConfig().addDefault("bots.nicks", args[1]);
            sender.sendMessage("§bSAntiBot §7| §fНикнейм §a" +args[1]+ " был §aуспешно §fдобавлен в память сервера");
            Main.getInstance().reloadConfig();
            return true;
        }

        return true;
    }
}
