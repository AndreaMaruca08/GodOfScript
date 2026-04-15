package game.logic.entity.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public final class DataSaver {
    private DataSaver() {}

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setVisibility(VisibilityChecker.Std.defaultInstance()
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

    private static final String FILE = "god_of_script_save.json";

    public static void savePlayer(Player player) {
        File file = new File(FILE);
        try {
            MAPPER.writeValue(file, player);
        }catch (IOException e){
            System.out.println("Failed to save player player: " + e.getMessage());
        }
    }

    public static Player loadPlayer() throws IOException {
        File file = new File(FILE);

        if (!file.exists()) {
            File currentDir = new File(".");
            String[] files = currentDir.list();
            if (files != null) {
                for (String f : files) {
                    System.out.println("  " + f);
                }
            }
            throw new IOException("Save file not found");
        }

        return MAPPER.readValue(file, Player.class);
    }

    public static Player getPlayer(){
        Player player;
        try {
            player = DataSaver.loadPlayer();
            player.initializeScriptsAfterLoad();
        }catch (Exception e){
            player = createPlayer();
        }
        return player;
    }

    public static Player deletePlayer(){
        File file = new File(FILE);
        if(file.delete()){
            return null;
        }
        return getPlayer();
    }

    public static Player createPlayer(){
        Player player;
        JOptionPane.showMessageDialog(null, "Starting a new game");
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        if(playerName == null)
            System.exit(0);
        player = new Player(playerName);
        DataSaver.savePlayer(player);
        return player;
    }


}