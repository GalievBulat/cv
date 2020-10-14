package DAO;

public class CommandHasntWorkedException extends Exception {
    public CommandHasntWorkedException(String message) {
        super(message);
    }
}
