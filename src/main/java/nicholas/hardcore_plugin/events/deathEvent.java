package nicholas.hardcore_plugin.events;

import nicholas.hardcore_plugin.HC_Player;
import nicholas.hardcore_plugin.Hardcore_Plugin;
import nicholas.hardcore_plugin.TeamsManager;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class deathEvent implements Listener {

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){

        final HashMap<UUID, HC_Player> playerList = Hardcore_Plugin.getPlayers();
        final UUID playerUUID = e.getPlayer().getUniqueId();

        HC_Player player = playerList.get(playerUUID);

        if(player != null){
            if(player.getGraceEnabled()){
                return;
            }

            // Update Lives logic
            player.updateLives(-1);

            Hardcore_Plugin.updatePlayers(playerList);
            TeamsManager.assignPlayerToTeam(e.getPlayer(), player.getLives());

            // Play lightning sound to all players
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0F, 1.0F);
            }
        }
    }
}
