package nicholas.hardcore_plugin.events;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.Hardcore_Plugin;
import nicholas.hardcore_plugin.Msg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;
import java.util.UUID;

public class respawnEvent implements Listener {

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent e){

        final HashMap<UUID, HC_Player> playerList = Hardcore_Plugin.getPlayers();
        final UUID playerUUID = e.getPlayer().getUniqueId();

        HC_Player hcp = playerList.get(playerUUID);

        if(hcp.getGraceEnabled()){
            Msg.send(e.getPlayer(), "&rYou have died during your grace period. You have &l&2"+hcp.getCurrentGrace()+"&r minutes remaining in your grace period.");}

    }
}
