package com.aibrhmckr.todo.Service;

import com.aibrhmckr.todo.Model.Response;
import com.aibrhmckr.todo.Model.Todo;
import com.aibrhmckr.todo.Model.User;

import java.util.List;

public interface IUserService {
    Response<List<User>> getUsers();

    Response<User> getUser(String username);

    Response<User> updateUser(String username, User uptadedUser);

    Response<User> deleteUser(String username);

    Response<User> addUser(User user);

    Response<Todo> getTodo(String username, String todoId);

    Response<Todo> addTodo(String username, Todo todo);

    Response<Todo> deleteTodo(String username, String todoId);
    Response<Todo> updateTodo(String username,String todoId,Todo uptadedTodo);
}
