package game.logic.entity;

import game.graphic.shared.Colors;

import java.awt.*;

public class Player extends Entity{
    public Player() {
        super(100, 10, 5, "PLAYER");
    }

    @Override
    public Color getColor(){
        return getHp() > getMaxHp() * 0.6 ? Colors.SUCCESS : getHp() > getMaxHp() * 0.3 ? Colors.WARNING : Colors.ERROR.brighter();
    }
}
