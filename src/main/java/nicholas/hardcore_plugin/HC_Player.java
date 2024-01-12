package nicholas.hardcore_plugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;


public class HC_Player {

    private int lives;
    private final String player;
    private final UUID playerUUID;
    private boolean graceEnabled;
    private int currentGrace;

    public HC_Player(Player player){
        final FileConfiguration config = Hardcore_Plugin.config();

        this.lives = config.getInt("lives");
        this.player = player.getName();
        this.playerUUID = player.getUniqueId();

        this.graceEnabled = config.getBoolean("grace_period");
        if(this.graceEnabled){
            this.currentGrace = config.getInt("grace_period_length");
        }else{
            this.currentGrace = 0;
        }
    }

    public int getLives(){return this.lives;}
    public void updateLives(int update){
        this.lives += update;
    }

    public String getPlayerName(){return this.player;}
    public UUID getPlayerUUID(){return this.playerUUID;}

    public boolean getGraceEnabled(){return this.graceEnabled;}
    public void setGrace(boolean setting){
        graceEnabled = setting;
    }
    public void updateCurrentGrace(int update){
        this.currentGrace += update;
    }
    public int getCurrentGrace(){return this.currentGrace;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HC_Player hcPlayer = (HC_Player) o;
        return Objects.equals(playerUUID, hcPlayer.getPlayerUUID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerUUID);
    }

}
