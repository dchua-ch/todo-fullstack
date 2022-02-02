package com.example.todo.repo;

import com.example.todo.model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TodoRepo extends JpaRepository<Todo,Integer>{
    
}
