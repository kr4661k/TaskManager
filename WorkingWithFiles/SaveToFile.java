package com.tkachenkopetr.spring.WorkingWithFiles;

import com.tkachenkopetr.spring.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SaveToFile {

    public void saveTasksToFile(List<Task> tasks, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            System.out.println("Задачи успешно сохранены в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
            throw new RuntimeException("Failed to save tasks to file", e);
        }
    }
}