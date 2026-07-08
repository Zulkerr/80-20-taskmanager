package com.example.taskmanager.service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    // ALle Tasks abrufen
    public List<Task> getALlTasks(){
        return taskRepository.findAll();
    }

    // Neuen Task erstellen
    public Task createTask(String title){
        Task newTask = new Task(title);
        return taskRepository.save(newTask);
    }

    // Einen Task als erledigt makieren
    public Task completeTask(Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setCompleted(true);
            return taskRepository.save(task);
        } else{
            throw new RuntimeException("Task mit der ID" + id + "wurde nicht gefunden.");
        }
    }

    // Einen Task löschen
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
