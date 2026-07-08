package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.dto.TaskCreateDto;
import com.example.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getALlTasks(){
        return taskService.getALlTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto dto){
        Task createdTask = taskService.createTask(dto.title());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id){
        return taskService.completeTask(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
