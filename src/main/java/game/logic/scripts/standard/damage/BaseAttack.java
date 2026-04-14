package game.logic.scripts.standard.damage;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.DeadEnemies;
import game.logic.board.exceptions.DeadPlayer;
import game.logic.entity.Entity;
import game.logic.entity.player.DataSaver;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

public class BaseAttack extends Script {
    public BaseAttack() {
        String desc = "Perform a basic attack around you";
        super("base attack",
                Command.cmd("base attack", desc),
                Command.cmd("attack", desc),
                Command.cmd("atk", desc)
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position pos = entity.getPosition();
        return board.aoeAction(pos, 1, targetPos -> {
            try {
                board.damageTo(targetPos, entity.getBaseAttack());
            }catch (DeadPlayer d){
                d.getPlayer().reset();
                DataSaver.savePlayer(d.getPlayer());
                return Event.DEAD;
            }catch (DeadEnemies d){
                d.getPlayer().reset();
                DataSaver.savePlayer(d.getPlayer());
                return Event.WIN;
            }
            return Event.OK;
        });
    }
}
