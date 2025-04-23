package main.Projects.FirstProject.Exceptions;

public class NotValidAuthor extends RuntimeException {
    public NotValidAuthor(String message) {
        super(message);
    }
}
