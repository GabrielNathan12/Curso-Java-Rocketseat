package br.com.gabrielnathan.todolist.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    
    @GetMapping("/task")
    public void create(){
        System.out.println("Hello Docker");
    }
}
