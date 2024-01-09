package nicholas.hardcore_plugin.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nicholas.hardcore_plugin.HC_Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class PlayerData {



    public static void savePlayerData(HashSet<HC_Player> players) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("plugins/Hardcore_Plugin/playerdata.json")) {
            gson.toJson(players, writer); // Serialize the HashSet and write it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashSet<HC_Player> loadPlayers() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("plugins/Hardcore_Plugin/playerdata.json")) {
            // Read the JSON as a HashSet of HC_Player objects
            return gson.fromJson(reader, new TypeToken<HashSet<HC_Player>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}