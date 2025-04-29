package com.tkachenkopetr.spring;

import com.tkachenkopetr.spring.Exceptions.NotUniqueEmail;
import com.tkachenkopetr.spring.Exceptions.NotValidEmail;
import com.tkachenkopetr.spring.Exceptions.NotValidName;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class User {
    private static final Pattern EMAIL_CHECKER = Pattern.compile("^\\w+([.-]?\\w+)*@[A-Za-z]\\.[A-Za-z]{2,6}$"); //final - UPPER_CASE
    private static final Pattern NAME_CHECKER = Pattern.compile("^[A-Za-zА-Яа-яёЁ]+([-\\s][A-Za-zА-Яа-яёЁ]+)*$");
    private static final Set<String> setOfEmails = new HashSet<>();
    private String name;
    private String email;

    public User(String name, String email){
        validateAndSetEmail(email);
        validateAndSetName(name);
    }

    public String getName() {return name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {validateAndSetEmail(email);}
    public void setName(String name){validateAndSetName(name);}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", email: ").append(email);
        return sb.toString();
    }

    private void validateAndSetEmail(String email) {
        if (!EMAIL_CHECKER.matcher(email).find()) {
            throw new NotValidEmail("Sorry, email: " + email + " is not correct");
        }
        if (this.email == null) {
            if (setOfEmails.contains(email)) {
                throw new NotUniqueEmail("Sorry, email: " + email + " is already in use. Try another one.");
            } else {
                setOfEmails.add(email);
                this.email = email;
            }
        } else {
            if (!this.email.equals(email)) {
                if (setOfEmails.contains(email)) {
                    throw new NotUniqueEmail("Sorry, email: " + email + " is already in use. Try another one.");
                } else {
                    setOfEmails.remove(this.email);
                    setOfEmails.add(email);
                    this.email = email;
                }
            }
        }
    }

    private void validateAndSetName(String name){
        if(NAME_CHECKER.matcher(name).matches()){
            this.name = name;
        } else{
            throw new NotValidName("The name you entered is invalid. It must contain only letters.");
        }
    }
    public static void clearEmails() {
        setOfEmails.clear();
    }
}