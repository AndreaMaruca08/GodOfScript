package game.graphic.components.title;

import core.components.ScalePressableComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.sound.Sounds;

public class TitleButton extends ScalePressableComponent {
    private final String content;
    private final Runnable action;

    public TitleButton(Dim dim, String content, Runnable action) {
        super(dim);
        this.content = content;
        this.action = action;
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawRoundRectBorder(dim, 2, Colors.PRIMARY);
        g.drawRoundRect(dim, 2, Colors.SECONDARY);
        g.drawText(dim, content, Colors.TEXT);
    }

    @Override
    public void press() {
        Sounds.clickSound();
        action.run();
    }
}
