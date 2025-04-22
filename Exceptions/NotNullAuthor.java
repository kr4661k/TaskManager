package main.Projects.FirstProject.Exceptions;

public class NotNullAuthor extends RuntimeException {
    public NotNullAuthor(String message) {
        super(message);
    }
}
