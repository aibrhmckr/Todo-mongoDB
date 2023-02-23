package com.aibrhmckr.todo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private List<Todo> todoList;

}
