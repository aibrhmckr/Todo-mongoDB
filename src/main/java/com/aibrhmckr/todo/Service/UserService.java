package com.aibrhmckr.todo.Service;

import com.aibrhmckr.todo.Model.Response;
import com.aibrhmckr.todo.Model.Todo;
import com.aibrhmckr.todo.Model.User;
import com.aibrhmckr.todo.Respository.TodoRepository;
import com.aibrhmckr.todo.Respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;


    @Override
    public Response<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return Response.Success(users);
    }

    @Override
    public Response<User> getUser(String username) {
        try {
            var found = userRepository
                    .findAll()
                    .stream()
                    .filter(x -> x.getUsername()
                            .equals(username))
                    .findFirst()
                    .orElse(null);
            if (found == null) {
                return Response.Fail("User not Found!");
            }
            return Response.Success(found);
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }

    }

    @Override
    public Response<User> updateUser(String username, User uptadedUser) {
        try {
            var result = findUserByUsername(username);
            if (result != null) {
                if(uptadedUser.getName()==null||uptadedUser.getSurname()==null){
                    return Response.Fail("Name and surname cannot be blank!");
                }
                else{
                    result.setName(uptadedUser.getName());
                    result.setSurname(uptadedUser.getSurname());
                    userRepository.save(result);
                    return Response.Success(result);
                }
            } else {
                return Response.Fail("User Not Found!");
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    @Override
    public Response<User> deleteUser(String username) {
        //userRepository.deleteById(username);
        var result = userRepository.findById(username).orElse(null);
        try {
            if (result == null) {
                return Response.Fail("User not found!");
            } else {
                userRepository.delete(result);
                if (!result.getTodoList().isEmpty()) {
                    for (var x : result.getTodoList()) {
                        todoRepository.delete(x);
                    }
                }
                return Response.Success(result);
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    @Override
    public Response<User> addUser(User user) {
        try {
            var found = userRepository
                    .findAll()
                    .stream()
                    .filter(x -> x.getUsername()
                            .equals(user.getUsername()))
                    .findFirst()
                    .orElse(null);
            if (found != null) {
                return Response.Fail("User already exists");
            } else {
                if (user.getUsername() == null || user.getPassword() == null) {
                    return Response.Fail("Username and password cannot be left blank!");
                } else {
                    user.setTodoList(new ArrayList<>());
                    userRepository.save(user);
                    return Response.Success(user);
                }
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    @Override
    public Response<Todo> getTodo(String username, String todoId) {
        try {
            var user = findUserByUsername(username);
            if (user != null) {
                var result = todoRepository.findAll().stream().filter(x -> x.getId().equals(todoId)).findFirst().orElse(null);
                if (result != null) {
                    return Response.Success(result);
                } else {
                    return Response.Fail("Todo is not found!");
                }
            } else {
                return Response.Fail("User Not Found");
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    @Override
    public Response<Todo> addTodo(String username, Todo todo) {
        try {
            var user = userRepository.findById(username).orElse(null);
            if (user != null) {
                if (todo.getTodoName() != null) {
                    var todoList = user.getTodoList();
                    todoList.add(todo);
                    user.setTodoList(todoList);
                    todoRepository.save(todo);/*Todo otomatik id atamsı için yaptım gerek kalmazsa silerim*/
                    userRepository.save(user);
                    return Response.Success(todo);
                } else {
                    return Response.Fail("Todo name cannot be blank!");
                }
            } else {
                return Response.Fail("User Not Found!");
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    @Override
    public Response<Todo> deleteTodo(String username, String todoId) {
        try {
            var user = findUserByUsername(username);
            if (user != null) {
                var result = todoRepository.findAll().stream().filter(x -> x.getId().equals(todoId)).findFirst().orElse(null);
                if (result != null) {
                    var todoList = user.getTodoList();
                    todoList.remove(result);
                    user.setTodoList(todoList);
                    todoRepository.delete(result);////*/*/
                    userRepository.save(user);
                    return Response.Success(result);

                } else {
                    return Response.Fail("Todo is not found!");
                }
            } else {
                return Response.Fail("User Not Found!");
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }

    }

    @Override
    public Response<Todo> updateTodo(String username, String todoId, Todo uptadedTodo) {//sorun var
        try {
            var result = findUserByUsername(username);
            if (result != null) {
                var userTodo = result.getTodoList()
                        .stream()
                        .filter(x -> x.getId()
                                .equals(todoId))
                        .findFirst()
                        .orElse(null);
                if (userTodo != null) {
                    userTodo.setTodoName(uptadedTodo.getTodoName());
                    userTodo.setFinished(uptadedTodo.isFinished());
                    userRepository.save(result);
                    todoRepository.save(userTodo);
                    return Response.Success(userTodo);
                } else {
                    return Response.Fail("Todo is not found!");
                }
            } else {
                return Response.Fail("User Not Found!");
            }
        } catch (Exception e) {
            return Response.Fail(e.getMessage());
        }
    }

    public User findUserByUsername(String username) {
        return userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    }
}
