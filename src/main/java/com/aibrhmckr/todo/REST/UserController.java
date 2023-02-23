package com.aibrhmckr.todo.REST;

import com.aibrhmckr.todo.Model.Response;
import com.aibrhmckr.todo.Model.Todo;
import com.aibrhmckr.todo.Model.User;
import com.aibrhmckr.todo.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor

@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<Response<List<User>>> getUsers() {
        var response = userService.getUsers();
        if (response.getIsSuccess()) {
            return ResponseEntity.ok(response);
        }
        //return new ResponseEntity<>(userService.getUsers(), OK);
        else return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Response<User>> getUser(@PathVariable String username) {
        var response = userService.getUser(username);
        if (response.getIsSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("")
    public ResponseEntity<Response<User>> addUser(@RequestBody User user) {
        var response = userService.addUser(user);
        if (response.getIsSuccess()) {
            return ResponseEntity.ok(response);
        } else return ResponseEntity.badRequest().body(response);
    }
    @PutMapping("/{username}")
    public ResponseEntity<Response<User>> updateUser(@RequestBody User uptadedUser, @PathVariable String username) {
        var response = userService.updateUser(username, uptadedUser);
        if (response.getIsSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<Response<User>> deleteUser(@PathVariable String username) {
        var response = userService.deleteUser(username);
        if (response.getIsSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/{username}/{todoId}")
    public ResponseEntity<Response<Todo>> getTodo(@PathVariable String username, @PathVariable String todoId){
        var response = userService.getTodo(username,todoId);
        if(response.getIsSuccess()){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/{username}")
    public ResponseEntity<Response<Todo>> addTodo(@PathVariable String username,@RequestBody Todo todo){
        var response = userService.addTodo(username,todo);
        if(response.getIsSuccess()){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping("/{username}/{todoId}")
    public ResponseEntity<Response<Todo>> deleteTodo(@PathVariable String username,@PathVariable String todoId){
        var response=userService.deleteTodo(username,todoId);
        if(response.getIsSuccess()){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/{username}/{todoId}")
    public ResponseEntity<Response<Todo>> updateTodo(@PathVariable String username,@PathVariable String todoId,@RequestBody Todo uptadedTodo){
        var response=userService.updateTodo(username,todoId,uptadedTodo);
        if(response.getIsSuccess()){
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }

}
