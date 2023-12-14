package br.com.gabrielnathan.todolist.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RestController
@RequestMapping("/tasks")
@ResponseBody

public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

   
    @GetMapping
    public ResponseEntity onRequest(){
        return ResponseEntity.ok(taskService.getAllTask());
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
       return ResponseEntity.ok(taskService.createTask(task));
    }
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task t = taskService.updateTask(task);
        
        if(t != null){
            return ResponseEntity.ok(t);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        List<Task> deleteTask = taskService.deleteTask(id);

        if(deleteTask != null){
            return ResponseEntity.ok(deleteTask);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
