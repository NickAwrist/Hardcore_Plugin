package nicholas.hardcore_plugin.events;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.Msg;
import nicholas.hardcore_plugin.TeamsManager;
import nicholas.hardcore_plugin.Hardcore_Plugin;

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
        final FileConfiguration config = Hardcore_Plugin.config();

        HC_Player hcp = new HC_Player(e.getPlayer());
        int currentGrace = hcp.getCurrentGrace();

        if(!playerList.containsKey(hcp.getPlayerUUID())){
            Hardcore_Plugin.addPlayer(hcp);

            TeamsManager.assignPlayerToTeam(e.getPlayer(), config.getInt("lives"));
        } else{
            hcp = playerList.get(e.getPlayer().getUniqueId());
            currentGrace = hcp.getCurrentGrace();
            TeamsManager.assignPlayerToTeam(e.getPlayer(), hcp.getLives());

        }

        if(hcp.getGraceEnabled()){
            Msg.send(e.getPlayer(), "&rYour grace period is enabled. You have &2"+currentGrace+"&r minutes left.");
        }
    }

}
