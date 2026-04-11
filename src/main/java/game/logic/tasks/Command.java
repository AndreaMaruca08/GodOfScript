package game.logic.tasks;

public record Command(
        String command,
        String description,
        TypeOfParam[] params
) {
    public static Command cmd(String command, String description, TypeOfParam... params) {
        return new Command(command, description, params);
    }

    public boolean matches(String input) {
        String[] parts = input == null ? new String[0] : input.trim().split("\\s+");

        if (parts.length == 0) {
            return false;
        }

        if (!command.equalsIgnoreCase(parts[0]))
            return false;

        if (parts.length - 1 != params.length)
            return false;

        for (int i = 0; i < params.length; i++) {
            if (!params[i].validate(parts[i + 1])) {
                return false;
            }
        }

        return true;
    }

    public String[] extractArgs(String input) {
        String[] parts = input.trim().split("\\s+");
        String[] args = new String[parts.length - 1];

        System.arraycopy(parts, 1, args, 0, args.length);
        return args;
    }
}