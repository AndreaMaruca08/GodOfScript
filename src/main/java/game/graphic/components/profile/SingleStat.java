package game.graphic.components.profile;

import core.components.ScalePage;
import core.components.ScalePressableComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.sound.Sounds;

public class SingleStat extends ScalePressableComponent {
    private final String name;
    private final Runnable action;
    private final ScalePage page;

    public SingleStat(Dim dim, ScalePage page, String name, Runnable action) {
        super(dim, "SingleStat");
        this.page = page;
        this.name = name;
        this.action = action;
    }


    @Override
    public void draw(ScaleGraphic g) {
        g.drawRoundRect(dim, 2, Colors.PRIMARY.brighter());
        g.drawTextLeft(dim, name + " ".repeat(18 - name.length()) + "Upgrade here", Colors.TEXT);
    }

    @Override
    public void press() {
        Sounds.clickSound();
        action.run();
        page.update(new Dim(0,0, 40,50));
    }
}
