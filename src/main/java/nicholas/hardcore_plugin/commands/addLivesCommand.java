package nicholas.hardcore_plugin.commands;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.Hardcore_Plugin;
import nicholas.hardcore_plugin.Msg;
import nicholas.hardcore_plugin.TeamsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class addLivesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length < 2){
            Msg.send(commandSender, "&rUsage: /add_lives <player> <amount>");
            return false;
        }

        boolean playerSender = commandSender instanceof Player;

        Player player = null;
        if(playerSender) {
            player = (Player) commandSender;

            if(!player.hasPermission("hardcore.addLives") && !player.isOp()){
                Msg.send(player, "&4You do not have permission for this command.");
                return true;
            }
        }

        Player target = Bukkit.getPlayer(strings[0]);

        if(target == null){

            if(playerSender){
                Msg.send(player, "&rPlayer not found");
            }else{
                Bukkit.getLogger().warning("Player not found");
            }

            return true;
        }

        HashMap<UUID, HC_Player> players = Hardcore_Plugin.getPlayers();
        HC_Player hcp = players.get(target.getUniqueId());

        int updateVal = Integer.parseInt(strings[1]);

        hcp.updateLives(updateVal);

        TeamsManager.assignPlayerToTeam(target, hcp.getLives());
        Hardcore_Plugin.updatePlayers(players);

        if(playerSender){
            Msg.send(player, "&r"+target.getName()+"&r now has &a"+hcp.getLives()+"&r lives");
            player.playSound(player.getLocation(), "minecraft:block.note_block.pling", 1.0F, 2.0F);
        }else{
            Bukkit.getLogger().info(target.getName()+" now has "+hcp.getLives()+" lives");
        }

        if(target.isOnline()){
            Msg.send(target, "&rYou have gained "+updateVal+" lives.");
            target.playSound(target.getLocation(), "minecraft:block.note_block.pling", 1.0F, 2.0F);
        }

        return true;
    }
}
