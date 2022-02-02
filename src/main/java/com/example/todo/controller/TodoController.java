package com.example.todo.controller;

import java.util.List;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoRepo tRepo;

    @GetMapping("/list")
    public List<Todo> getTodos() {
        List<Todo> todos = tRepo.findAll();

        return todos;
        
    }

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        if(todo != null) {
            tRepo.saveAndFlush(todo);
        }

        return todo;
        
    }

    @PutMapping("/toggle/{id}")
    public void toggleComplete(@PathVariable Integer id) {
        Todo todo = tRepo.findById(id).get();

        if(todo != null) {
            todo.setCompleted(!todo.getCompleted());
            tRepo.saveAndFlush(todo);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Integer id){
        Todo todo = tRepo.findById(id).get();

        if(todo != null) {
            tRepo.delete(todo);
        }

    }

    
    
}
