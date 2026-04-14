package game.graphic.components.board;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.board.Board;
import game.logic.board.Tile;

public class GraphicBoard extends ScaleComponent {
    private final Board board;
    private final GraphicPlayerStatus playerStatus;

    public GraphicBoard(Dim dim, Board board) {
        super(dim, "GraphicBoard");
        this.board = board;
        this.playerStatus = new GraphicPlayerStatus(new Dim(dim.x(), dim.y() - 4, dim.width(), 4), board.getPlayer());
    }

    @Override
    public void draw(ScaleGraphic g) {
        double tileWidth = dim.width() / board.getWidth();
        double tileHeight = dim.height() / board.getHeight();

        for(int x = 0; x < board.getTiles().length; x++){
            for(int y = 0; y < board.getTiles()[x].length; y++){
                Tile tile = board.getTiles()[x][y];

                double tileX = dim.x() + (x * tileWidth);
                double tileY = dim.y() + (y * tileHeight);

                Dim tileDim = new Dim(tileX, tileY, tileWidth, tileHeight);

                if(tile != null) {
                    g.draw(new GraphicTile(tileDim, tile, board.isNames()));
                }
            }
        }

        grill(g, tileWidth, tileHeight);

        g.draw(playerStatus);
    }

    private void grill(ScaleGraphic g, double tileWidth, double tileHeight){
        g.changeDrawWidth(0.1);
        g.setColor(Colors.THIRD);

        for(int x = 0; x <= board.getWidth(); x++) {
            double lineX = dim.x() + (x * tileWidth);
            g.line(lineX, dim.y(), lineX, dim.y() + dim.height());
        }

        for(int y = 0; y <= board.getHeight(); y++) {
            double lineY = dim.y() + (y * tileHeight);
            g.line(dim.x(), lineY, dim.x() + dim.width(), lineY);
        }
    }

}