package nicholas.hardcore_plugin;

import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;


public class HC_Player {

    private int lives;
    private final String player;
    private final UUID playerUUID;

    public HC_Player(Player player){
        this.lives = Hardcore_Plugin.config().getInt("lives");
        this.player = player.getName();
        this.playerUUID = player.getUniqueId();

    }

    public int getLives(){return this.lives;}
    public void updateLives(int update){
        this.lives += update;
    }
    public boolean isAlive(){return this.lives > 0;}

    public String getPlayerName(){return this.player;}
    public UUID getPlayerUUID(){return this.playerUUID;}

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
