package game.logic.scripts;

public record ConsoleLog (
        String message,
        Event event
){
    public static ConsoleLog logOf(String message, Event event) {
        return new ConsoleLog("> "+message, event);
    }
}
