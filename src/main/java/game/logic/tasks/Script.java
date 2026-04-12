package game.logic.tasks;

import game.logic.board.Board;
import game.logic.entity.Entity;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Script {
    private final List<Command> commands;
    protected final String name;
    private String[] currentArgs = new String[0];

    protected Script(String name, Command... commands) {
        this.commands = Arrays.stream(commands).toList();
        this.name = "---"+name.toUpperCase()+"---";
    }

    public String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n  ");
        for (Command command : commands) {
            sb.append(command.command())
                    .append(" ")
                    .append(command.description())
                    .append(" ")
                    .append(Arrays.toString(command.params()))
                    .append("\n  ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public boolean isValid(String input) {
        for (Command cmd : commands) {
            if (cmd.matches(input)) {
                currentArgs = cmd.extractArgs(input);
                return true;
            }
        }
        return false;
    }

    protected String getArg(int index) {
        return currentArgs[index];
    }

    protected int getIntArg(int index) {
        return Integer.parseInt(currentArgs[index]);
    }

    public abstract Event run(Entity player, Board board) throws Exception;
}