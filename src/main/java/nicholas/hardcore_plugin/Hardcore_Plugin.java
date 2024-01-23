package nicholas.hardcore_plugin;

import nicholas.hardcore_plugin.commands.addLivesCommand;
import nicholas.hardcore_plugin.commands.hcTopCommand;
import nicholas.hardcore_plugin.commands.setLivesCommand;
import nicholas.hardcore_plugin.events.deathEvent;
import nicholas.hardcore_plugin.events.joinEvent;
import nicholas.hardcore_plugin.events.respawnEvent;
import nicholas.hardcore_plugin.files.PlayerData;

import org.bukkit.Bukkit;
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

        if(plugin.getConfig().getInt("lives") <= 0){
            Bukkit.getLogger().severe("Hardcore | LIVES IS SET TO LESS THAN 0 IN CONFIG. IT MUST BE GREATER THAN 0!");
            return;
        }


        PlayerData.setup();

        playerList = PlayerData.loadPlayers();

        getServer().getPluginManager().registerEvents(new joinEvent(), plugin);
        getServer().getPluginManager().registerEvents(new deathEvent(), plugin);
        getServer().getPluginManager().registerEvents(new respawnEvent(), plugin);

        this.getCommand("add_lives").setExecutor(new addLivesCommand());
        this.getCommand("set_lives").setExecutor(new setLivesCommand());
        this.getCommand("hc_top").setExecutor(new hcTopCommand());

        TeamsManager.setupScoreboard();

        new GracePeriodTask().runTaskTimer(this, 0L, 1200L);

        Bukkit.getLogger().info("Hardcore | Hardcore Plugin has been enabled");
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
        playerList = updatedPlayerList;
        PlayerData.savePlayerData(updatedPlayerList);
    }

    public static HashMap<UUID, HC_Player> getPlayers(){return playerList;}
    public static void addPlayer(HC_Player player){
        playerList.put(player.getPlayerUUID(), player);
        updatePlayers();
    }
}
