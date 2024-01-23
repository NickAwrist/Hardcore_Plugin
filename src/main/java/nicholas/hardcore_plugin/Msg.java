package nicholas.hardcore_plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg {
    public static void send(CommandSender sender, String message, String prefix){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void send(CommandSender sender, String message){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Hardcore | "+message));
    }
}