package demos.taskManagementApp.controller;

import demos.taskManagementApp.model.Task;
import demos.taskManagementApp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ihsan/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        Task task = null;
        task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);

    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteTask(@PathVariable("id") Long id){
        boolean deleted = false;
        deleted = taskService.deleteTask(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        task = taskService.updateTask(id,task);
        return ResponseEntity.ok(task);
    }


}
