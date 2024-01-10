package nicholas.hardcore_plugin.events;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.TeamsManager;
import nicholas.hardcore_plugin.Hardcore_Plugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;


public class joinEvent implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){

        final HashMap<UUID, HC_Player> playerList = Hardcore_Plugin.getPlayers();

        HC_Player newPlayer = new HC_Player(e.getPlayer());

        final FileConfiguration config = Hardcore_Plugin.config();

        if(!playerList.containsKey(newPlayer.getPlayerUUID())){
            Hardcore_Plugin.addPlayer(newPlayer);
            TeamsManager.assignPlayerToTeam(e.getPlayer(), config.getInt("lives"));
        } else{
            newPlayer = playerList.get(e.getPlayer().getUniqueId());
            TeamsManager.assignPlayerToTeam(e.getPlayer(), newPlayer.getLives());
        }

        Bukkit.getLogger().info("Player Joined");




    }

}
