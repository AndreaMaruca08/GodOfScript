package game.graphic.shared;

import java.awt.*;

public final class Fonts {
    private Fonts() {}
    public static Font PRIMARY(int size) {
        return new Font("monospaced", Font.PLAIN, size);
    }
}
