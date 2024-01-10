package nicholas.hardcore_plugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import nicholas.hardcore_plugin.Hardcore_Plugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;


public class TeamsManager {

    private static ScoreboardManager manager;
    private static Scoreboard scoreboard;
    private static Team redTeam, yellowTeam, greenTeam, spectatorTeam;
    private static Objective livesObjective;

    public static void setupScoreboard(){
        manager = Bukkit.getScoreboardManager();

        scoreboard = manager.getNewScoreboard();

        livesObjective = scoreboard.registerNewObjective("Lives", Criteria.DUMMY, Component.text("Lives"));
        livesObjective.setDisplaySlot(DisplaySlot.PLAYER_LIST);


        redTeam = scoreboard.registerNewTeam("Red");
        redTeam.color(NamedTextColor.RED);

        yellowTeam = scoreboard.registerNewTeam("Yellow");
        yellowTeam.color(NamedTextColor.YELLOW);

        greenTeam = scoreboard.registerNewTeam("Green");
        greenTeam.color(NamedTextColor.GREEN);

        spectatorTeam = scoreboard.registerNewTeam("Spectator");
        spectatorTeam.color(NamedTextColor.GRAY);

    }

    public static void assignPlayerToTeam(Player player, int lives){

        Team currentTeam = scoreboard.getPlayerTeam(player);
        Score score = livesObjective.getScore(player);
        score.setScore(lives);

        if(currentTeam != null){
            currentTeam.removePlayer(player);
            player.setGameMode(GameMode.SURVIVAL);
        }

        final FileConfiguration config = Hardcore_Plugin.config();
        int maxLives = config.getInt("lives");

        double place = (double) lives / (double) maxLives;
        place = Math.floor(place * 100) / 100.0;

        if(place > 0.66){
            greenTeam.addPlayer(player);
        }else if(place > 0.33){
            yellowTeam.addPlayer(player);
        }else if(place > 0.0){
            redTeam.addPlayer(player);
        }else{
            spectatorTeam.addPlayer(player);
            player.setGameMode(GameMode.SPECTATOR);
        }

        player.setScoreboard(scoreboard);
    }
}
