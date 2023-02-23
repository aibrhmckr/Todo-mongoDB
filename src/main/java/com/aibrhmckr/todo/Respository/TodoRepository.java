package com.aibrhmckr.todo.Respository;

import com.aibrhmckr.todo.Model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo,String> {
}
