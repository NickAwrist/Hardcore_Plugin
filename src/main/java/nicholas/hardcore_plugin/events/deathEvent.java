package nicholas.hardcore_plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class deathEvent implements Listener {

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){

        final UUID playerUUID = e.getPlayer().getUniqueId();






    }

}
