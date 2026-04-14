package game.logic.scripts;

import game.logic.board.Board;
import game.logic.entity.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static game.logic.scripts.ConsoleLog.logOf;

@Getter
@Setter
public class Console {
    private final Entity entity;
    private final Board board;

    public Console(Board board) {
        this.board = board;
        this.entity = board.getPlayer();
    }

    private List<ConsoleLog> log = new ArrayList<>(25);

    public Event execute(String input) {
        try {
            for(Script task : entity.getScripts()){
                if(task.isValid(input)){
                    var res = task.run(entity, board);
                    addLog(logOf(input, res == null ? Event.ERROR : res));
                    return res;
                }
            }
            addLog(logOf(input, Event.NOT_FOUND));
            return Event.NOT_FOUND;
        } catch (Exception e) {
            addLog(logOf(input, Event.ERROR));
            return Event.ERROR;
        }
    }

    public void addLog(ConsoleLog message){
        if(log.size() > 23)
            clearLog();
        log.add(message);
    }

    public void clearLog(){
        log.clear();
    }
}
