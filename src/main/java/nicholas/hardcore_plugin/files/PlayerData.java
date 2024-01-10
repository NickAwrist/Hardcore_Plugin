package nicholas.hardcore_plugin.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nicholas.hardcore_plugin.HC_Player;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PlayerData {


    public static File file;

    public static void setup(){
        file = new File(Bukkit.getPluginManager().getPlugin("Hardcore_Plugin").getDataFolder(), "playerdata.json");

        if(!file.exists()){
            try{
                Bukkit.getLogger().warning("Hardcore | No playerdata.json found. Creating new file.");
                file.createNewFile();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }



    public static void savePlayerData(HashMap<UUID, HC_Player> players) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("plugins/Hardcore_Plugin/playerdata.json")) {
            gson.toJson(players, writer); // Serialize the HashSet and write it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<UUID, HC_Player> loadPlayers() {
        Gson gson = new Gson();
        file = new File(Bukkit.getPluginManager().getPlugin("Hardcore_Plugin").getDataFolder(), "playerdata.json");

        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(file)) {
            // Read the JSON as a HashSet of HC_Player objects
            HashMap<UUID, HC_Player> players = gson.fromJson(reader, new TypeToken<HashMap<UUID, HC_Player>>(){}.getType());
            return players != null ? players : new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }


}