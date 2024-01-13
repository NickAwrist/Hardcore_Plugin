package nicholas.hardcore_plugin;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class GracePeriodTask extends BukkitRunnable {

    @Override
    public void run() {

        final HashMap<UUID, HC_Player> playerList = Hardcore_Plugin.getPlayers();

        UUID playerUUID;
        HC_Player hcp;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            playerUUID = p.getUniqueId();
            hcp = playerList.get(playerUUID);

            if(hcp.getGraceEnabled()){
                hcp.updateCurrentGrace(-1);
                int currentGrace = hcp.getCurrentGrace();

                if (currentGrace <= 0) {
                    // Grace period is over
                    Msg.send(p, "&4Your grace period has ended.");
                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1.0F, 1.0F);

                    hcp.setGrace(false);

                    // 1 hour left
                } else if (currentGrace == 60) {
                    Msg.send(p, "&rYou have&4 1&r hour left in your grace period.");
                    p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0F, 1.0F);

                    // 30 minutes left
                } else if (currentGrace == 30) {
                    Msg.send(p, "&rYou have&4 30&r minutes left in your grace period.");
                    p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0F, 1.0F);

                    // 10 minutes left
                } else if (currentGrace == 10) {
                    Msg.send(p, "&rYou have&4 10&r minutes left in your grace period.");
                    p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0F, 1.0F);

                    // 5 minutes left
                }else if (currentGrace == 5) {
                    Msg.send(p, "&rYou have&4 5&r minutes left in your grace period.");
                    p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0F, 1.0F);

                    // 1 minute left
                }else if (currentGrace == 1) {
                    Msg.send(p, "&rYou have&4 1&r minute left in your grace period.");
                    p.playSound(p.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0F, 1.0F);
                }
            }

            Hardcore_Plugin.updatePlayers(playerList);



        }
    }
}
