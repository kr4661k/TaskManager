package com.tkachenkopetr.spring.WorkingWithFiles;

import com.tkachenkopetr.spring.Task;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoadFromFile {

    public List<Task> loadTasksFromFile(String filePath) {
        List<Task> loadedTasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                loadedTasks.add(task);
            }
            System.out.println("Задачи успешно загружены из файла: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки задач из файла: " + e.getMessage());
            throw new RuntimeException("Failed to load tasks from file", e);
        }

        return loadedTasks;
    }
}