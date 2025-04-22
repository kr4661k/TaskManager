package main.Projects.FirstProject.Exceptions;

public class WrongLengthOfString extends RuntimeException {
    public WrongLengthOfString(String message) {
        super(message);
    }
}
