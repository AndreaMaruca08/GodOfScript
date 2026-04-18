
import game.logic.entity.player.DataSaver;
import game.logic.entity.player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DataSaverTest {

    private static final String FILE = "god_of_script_save.json";
    private Path backup;

    @BeforeEach
    void backupSave() throws IOException {
        File f = new File(FILE);
        if (f.exists()) {
            backup = Files.createTempFile("gos_backup", ".json");
            Files.copy(f.toPath(), backup, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            Files.delete(f.toPath());
        }
    }

    @AfterEach
    void restoreSave() throws IOException {
        File f = new File(FILE);
        if (f.exists()) Files.delete(f.toPath());
        if (backup != null && Files.exists(backup)) {
            Files.copy(backup, f.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            Files.delete(backup);
        }
    }

    @Test
    void savePlayerCreatesFile() {
        Player p = new Player("TestUser");
        DataSaver.savePlayer(p);

        assertTrue(new File(FILE).exists());
    }

    @Test
    void loadPlayerAfterSaveReturnsEquivalentPlayer() throws IOException {
        Player p = new Player("Saved");
        p.setLevel(5);
        p.setPoints(2);
        DataSaver.savePlayer(p);

        Player loaded = DataSaver.loadPlayer();
        assertEquals("Saved", loaded.getName());
        assertEquals(5, loaded.getLevel());
        assertEquals(2, loaded.getPoints());
    }

    @Test
    void loadPlayerWithoutFileThrows() {
        assertThrows(IOException.class, DataSaver::loadPlayer);
    }

    @Test
    void savePlayerResetsStatsBeforeSaving() throws IOException {
        Player p = new Player("R");
        p.takeDamage(5);
        DataSaver.savePlayer(p);

        Player loaded = DataSaver.loadPlayer();
        assertEquals(loaded.getMaxHp(), loaded.getHp(), 0.0001);
    }
}