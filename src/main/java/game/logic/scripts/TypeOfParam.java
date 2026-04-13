package game.logic.scripts;

public enum TypeOfParam {
    INT {
        @Override
        public boolean validate(String value) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    STRING {
        @Override
        public boolean validate(String value) {
            return value != null && !value.isBlank();
        }
    };

    public abstract boolean validate(String value);
}