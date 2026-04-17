package game.graphic.components.profile;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.GraphicScript;
import game.logic.scripts.Script;

import java.util.List;

public class GraphicScriptList extends ScaleComponent {
    private final List<Script> scripts;
    private final int perRow;

    public GraphicScriptList(Dim dim, List<Script> scripts, int perRow) {
        super(dim);
        this.scripts = scripts;
        this.perRow = perRow;
    }

    @Override
    public void draw(ScaleGraphic g) {
        int scriptWidth = 18;
        int scriptHeight = 9;
        int spacingX = 2;
        int spacingY = 3;

        for(int i = 0; i < scripts.size(); i++){
            Script script = scripts.get(i);

            int row = i / perRow;
            int col = i % perRow;

            double x = 1+ dim.x() + (col * (scriptWidth + spacingX));
            double y = dim.y() + (row * (scriptHeight + spacingY));

            Dim scriptDim = new Dim(x, y, scriptWidth, scriptHeight);
            g.draw(new GraphicScript(scriptDim, script));
        }
    }
}