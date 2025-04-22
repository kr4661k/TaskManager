package main.Projects.FirstProject.Exceptions;

public class NotValidTask extends RuntimeException {
    public NotValidTask(String message) {
        super(message);
    }
}
