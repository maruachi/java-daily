package _20230504_tag;

public enum Command {
    CREATE("create"), EXEUCTE("execute");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command create(String value) {
        if (value == null) {
            throw new RuntimeException();
        }

        for (Command command : Command.values()){
            if (command.value.equalsIgnoreCase(value)) {
                return command;
            }
        }

        throw new RuntimeException();
    }
}
