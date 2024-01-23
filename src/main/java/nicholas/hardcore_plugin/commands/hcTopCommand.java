package nicholas.hardcore_plugin.commands;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.Hardcore_Plugin;
import nicholas.hardcore_plugin.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class hcTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)){
            Bukkit.getLogger().warning("This command can only be run by a player");
            return true;
        }

        Player player = ((Player) commandSender).getPlayer();

        assert player != null;
        if(!player.hasPermission("hardcore.hcTop") && !player.isOp()){
            Msg.send(commandSender, "&rYou do not have permission for this command.");
            return true;
        }

        HashMap<UUID, HC_Player> players = Hardcore_Plugin.getPlayers();

        int pageNum = 1;

        if(strings.length == 1){
            try{
                pageNum = Integer.parseInt(strings[0]);
            }catch (NumberFormatException e){
                commandSender.sendMessage("Invalid page number");
                return true;
            }
        }

        int maxPage = (int) Math.ceil(players.size()/10.0);
        StringBuilder message = new StringBuilder("\n&r&l&m-----------&r&l[ &r&l&4Leaderboard &r&l&m]&r&l&m-----------\n" +
                "&r&l&m-----------&r&l[ &r&l&2Page " + pageNum + " &r&l&m]&r&l&m-----------\n");

        int start = 0;

        if(pageNum > 1){
            start = (pageNum-1)*5;
        }

        int maxLives = Hardcore_Plugin.config().getInt("lives");
        for(int i = start; i<(5*pageNum)+5; i++){
            if(i >= players.size()){
                break;
            }
            UUID uuid = (UUID) players.keySet().toArray()[i];
            HC_Player hcp = players.get(uuid);

            int lives = hcp.getLives();

            double place = (double) lives / (double) maxLives;
            place = Math.floor(place * 100) / 100.0;

            char color = '3';

            if(place > 0.66){
                color = 'a';
            }else if(place > 0.33){
                color = '6';
            }else if(place > 0.0) {
                color = 'c';
            }

            message.append("&r&l").append(i + 1).append(". &r&").append(color).append(hcp.getPlayerName()).append("&r&l: &r&l&").append(color).append(hcp.getLives()).append("&r&l\n");
        }


        String finalMessage = message.toString();

        Msg.send(commandSender, "&r"+finalMessage, "");

        return true;
    }
}
