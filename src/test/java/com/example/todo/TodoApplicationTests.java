package com.example.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TodoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoApplicationTests {

	@Autowired
	private TodoRepo tRepo;

	@Test
	@Order(1)
	void contextLoads() {
	}

	//@Test
	@Order(2)
	void testAddTodos() {

		String[] titles = {
			"Walk the dog", 
			"Clean my room", 
			"Eat the fish", 
			"Burn the trash",
			"Fool around like a clown",
			"Do my AD project",
			"Don't worry, be happy",
			"Meow"
		};

		ArrayList<Todo> todos = new ArrayList<Todo>();

		for(String title : titles) {
			todos.add(new Todo(title));
		}

		tRepo.saveAllAndFlush(todos);

		List<Todo> storedTodos = tRepo.findAll();

		assertEquals(todos.size(),storedTodos.size());

	}


}
