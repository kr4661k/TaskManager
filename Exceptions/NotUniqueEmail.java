package main.Projects.FirstProject.Exceptions;

public class NotUniqueEmail extends RuntimeException {
    public NotUniqueEmail(String message) {
        super(message);
    }
}
