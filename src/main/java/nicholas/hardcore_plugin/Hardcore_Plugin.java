package nicholas.hardcore_plugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import nicholas.hardcore_plugin.files.PlayerData;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public final class Hardcore_Plugin extends JavaPlugin {

    private static Hardcore_Plugin plugin;
    HashSet<HC_Player> playerList;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        playerList = loadPlayers();

        /* TODO

              - Update player lives when death
              - Update player data file when death
              - Update player team when death
              - Player death effects
              - Set dead players to spectator


        */

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        updatePlayers();
    }

    @NotNull
    public static FileConfiguration config(){return plugin.getConfig();}

    private HashSet<HC_Player> loadPlayers(){return PlayerData.loadPlayers();}
    public void updatePlayers(){
        PlayerData.savePlayerData(playerList);
    }

    public HashSet<HC_Player> getPlayers(){return playerList;}
    public void addPlayers(HC_Player player){
        playerList.add(player);
    }
}
