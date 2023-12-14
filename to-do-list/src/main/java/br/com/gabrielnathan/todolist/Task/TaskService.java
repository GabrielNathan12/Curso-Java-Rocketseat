package br.com.gabrielnathan.todolist.Task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTask() {
        List<Task> task = taskRepository.findAll();
        return task;
    }

    public Task createTask(Task task){
       
        return taskRepository.save(task);
    }
    public Task updateTask(Task task){
        Task exist = taskRepository.findById(task.getId());

        if(exist != null){
            exist.setTitle(task.getTitle());
            exist.setDescription(task.getDescription());
            exist.setPosdate(task.getPosdate());
            exist.setEnddate(task.getEnddate());
            return taskRepository.save(exist);
        }
        return null;
    }
    
    public List<Task> deleteTask(Long id){
        Task task = taskRepository.findById(id);

        if(task != null){
            taskRepository.delete(task);
            return taskRepository.findAll();
        }
        return null;
    }

}
