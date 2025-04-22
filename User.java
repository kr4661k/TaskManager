package main.Projects.FirstProject;

import main.Projects.FirstProject.Exceptions.NotEmptyException;
import main.Projects.FirstProject.Exceptions.NotValidEmail;
import java.util.regex.Pattern;

public class User {
    private static final Pattern EMAIL_CHECKER = Pattern.compile("\\w+((-|.)?\\w*)?@(gmail|rambler|mail|yandex)?\\.(com|ru)"); //final - UPPER_CASE
    private final String name;
    private String email;

    public User(String name, String email){
        validateAndSetEmail(email);
        if(!name.isEmpty()) {
            this.name = name;
        }
        else{
            throw new NotEmptyException("Введено пустое имя");
        }
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {
        validateAndSetEmail(email);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", email: ").append(email);
        return sb.toString();
    }

    private void validateAndSetEmail(String email){
        if (EMAIL_CHECKER.matcher(email).find()) {
            this.email = email;
        } else{
            throw new NotValidEmail("Sorry, email: " + email + " is not correct");
        }
    }
}



