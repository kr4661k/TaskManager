package main.Projects.FirstProject;

import main.Projects.FirstProject.Exceptions.NotValidTask;
import main.Projects.FirstProject.Exceptions.TaskNotFound;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks;
    private final Set<String> emails;
    private final Map<Long, Task> taskMap;

    public TaskManager(){
        this.tasks = new ArrayList<>();
        this.emails = new HashSet<>();
        this.taskMap = new HashMap<>();
    }

    public void addTask(Task task){
        if(!taskMap.containsKey(task.getId())){
            taskMap.put(task.getId(), task);
            tasks.add(task);
            emails.add(task.getAuthor().getEmail());
        } else {
            throw new NotValidTask("Task: " + task + " уже был добавлен ранее, значения должны быть уникальны");
        }
    }

    public void removeTask(long id){
        if(taskMap.containsKey(id)){
            Task task = taskMap.get(id);
            taskMap.remove(id);
            emails.remove(task.getAuthor().getEmail());
            tasks.remove(task);
            System.out.println("Задача с id: " + id + " успешно удалена");
        } else{
            throw new NotValidTask("Задача с id: " + id + " не была найдена в списке задач");
        }
    }

    public List<Task> findByStatus(Status status){
        List<Task> statusList = tasks.stream().filter(a -> a.getStatus() == status).collect(Collectors.toList());
        if(!statusList.isEmpty()){
            System.out.println(statusList);
        }
        else{
            System.out.println("Задач с таким статусом нет");
        }
        return statusList;
    }

    public List<Task> findByAuthor(String name){
        boolean found = false;
        List<Task> authorList = new ArrayList<>();
        for(Task element: tasks){
            if(element.getAuthor().getName().equals(name)){
                if (!found) {found = true;}
                authorList.add(element);
            }
        }
        if(!found){
            System.out.println("Задачи такого автора не найдены");
            return authorList;
        }
        System.out.println(authorList);
        return authorList;
    }

    public void printAllTasks(){
        if(!tasks.isEmpty()){
            tasks.forEach(System.out::println);
        }
        else{
            System.out.println("Список задач пуст");
        }
    }

    public Set<String> getAllAuthors(){
        System.out.println(emails);
        return emails;
    }

    public void updateStatusById(long id, Status status){
        if(taskMap.containsKey(id)){
            taskMap.get(id).setStatus(status);
            System.out.println("Статус задачи " + taskMap.get(id) + " обновлен до: " + status);
        }
        else{
            throw new TaskNotFound("Задача с id: " + id + " не найдена");
        }
    }

    public List<Task> findByText(String text){
        List<Task> descList = tasks.stream().filter(a -> a.getDescription().contains(text)).collect(Collectors.toList());
        if(!descList.isEmpty()){
            System.out.println(descList);
        }
        else{
            System.out.println("Задачи с таким описанием найдено не было");
        }
        return descList;
    }

    public void saveToFile(String file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(Task element: tasks){
                writer.write(element.toFileFormat());
                writer.newLine();
            }
            System.out.println("Задачи сохранены в файл: " + file);

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач" + e.getMessage());
        }
    }

    public void loadFromFile(String file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String part;
            while((part = reader.readLine()) != null){
                Task task = Task.fromString(part);
                tasks.add(task);
            }
            System.out.println("Задачи успешно загружены из файла: " + file);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки задач из файла: " + file);
        }
    }
}
