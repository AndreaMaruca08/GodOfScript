package game.graphic.components;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.scripts.Script;

public class GraphicScript extends ScaleComponent {
    private final Script script;
    private final Dim commandSection;

    public GraphicScript(Dim dim, Script script) {
        super(dim, "GraphicScript");
        this.script = script;
        this.commandSection = dim.lower(2);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.changeDrawWidth(0.20);
        g.drawRoundRect(commandSection, 2, Colors.SECONDARY.darker().darker());
        g.drawRoundRectBorder(commandSection, 2, Colors.PRIMARY.darker().darker());
        g.font(0.8);
        g.drawText(commandSection.lower(3.5), "Cmd: \""+script.getCommands().getFirst().command()+"\"", Colors.TEXT);
        g.font(1.2);
        g.drawRoundRect(dim, 2, Colors.SECONDARY.darker());
        g.drawRoundRectBorder(dim, 2, Colors.PRIMARY.darker());
        g.drawText(dim, script.getName(), Colors.TEXT);
    }
}
