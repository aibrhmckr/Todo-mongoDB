package com.aibrhmckr.todo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
public class Todo {
    @Id
    private String id;
    private String todoName;
    private boolean isFinished;

}
