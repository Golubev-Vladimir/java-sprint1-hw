public enum Menu {
    INPUT("1"),
    STAT("2"),
    CHANGE("3"),
    EXIT("4");

    private final String command;

    Menu(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}