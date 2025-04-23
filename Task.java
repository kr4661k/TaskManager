package main.Projects.FirstProject;

import main.Projects.FirstProject.Exceptions.NotEmptyException;
import main.Projects.FirstProject.Exceptions.NotValidAuthor;
import main.Projects.FirstProject.Exceptions.WrongLengthOfString;

public class Task {

    public static Task fromString(String text){
        String[] parts = text.split("\\|");
        if(parts.length != 6){
            throw new WrongLengthOfString("Неверная длина строки");
        }

        long id = Long.parseLong(parts[0]);
        String title = parts[1];
        String description = parts[2];
        Status status = Status.valueOf(parts[3]);
        User author = new User(parts[4], parts[5]);

        return new Task(id, title, description, status, author);
    }

    public String toFileFormat() {
        return id + "|" + title + "|" + description + "|" + status + "|" + author.getName() + "|" + author.getEmail();
    }


    private static int idCounter = 0;

    private long id;
    private String title;
    private String description;
    private Status status;
    private User author;

    public Task(String title, String description, User author){
        this.id = ++idCounter;
        checkName(title);
        this.description = description;
        this.status = Status.TODO;
        checkAuthor(author);
    }

    public Task(long id, String title, String description, Status status, User author){ //Конструктор для работы с файлом
        this.id = id;
        checkName(title);
        this.description = description;
        this.status = status;
        checkAuthor(author);

        if (id > idCounter) {
            idCounter = (int) id;
        }
    }

    public long getId() {return id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {checkName(title);}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
    public User getAuthor() {return author;}
    public void setAuthor(User author) {checkAuthor(author);}

    @Override
    public String toString() {
        return String.format("Task№%d\nTitle: %s\nDescription: %s\nStatus: %s\nAuthor: %s\n",
                id, title, description, status, author.getName());
    }

    private void checkName(String title){
        if(title.isEmpty()){
            throw new NotEmptyException("Введено нулевое имя");
        } else{
            this.title = title;
        }
    }
    private void checkAuthor(User author){
        if(author != null){
            this.author = author;
        } else{
            throw new NotValidAuthor("Автор задачи не может быть null");
        }
    }
}