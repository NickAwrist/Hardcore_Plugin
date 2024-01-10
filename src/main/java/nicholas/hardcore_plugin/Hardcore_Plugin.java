package nicholas.hardcore_plugin;

import nicholas.hardcore_plugin.events.deathEvent;
import nicholas.hardcore_plugin.events.joinEvent;
import nicholas.hardcore_plugin.TeamsManager;

import nicholas.hardcore_plugin.files.PlayerData;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public final class Hardcore_Plugin extends JavaPlugin {

    private static Hardcore_Plugin plugin;
    static HashMap<UUID, HC_Player> playerList;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        PlayerData.setup();

        playerList = PlayerData.loadPlayers();

        getServer().getPluginManager().registerEvents(new joinEvent(), this);
        getServer().getPluginManager().registerEvents(new deathEvent(), this);

        TeamsManager.setupScoreboard();

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

    public static void updatePlayers(){
        PlayerData.savePlayerData(playerList);
    }

    public static void updatePlayers(HashMap<UUID, HC_Player> updatedPlayerList){
        PlayerData.savePlayerData(updatedPlayerList);
    }

    public static HashMap<UUID, HC_Player> getPlayers(){return playerList;}
    public static void addPlayer(HC_Player player){
        playerList.put(player.getPlayerUUID(), player);
        updatePlayers();
    }
}
