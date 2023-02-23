package com.aibrhmckr.todo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<E> {
    private Boolean isSuccess;
    private String message;
    private E body;

    public static <E> Response Success(E body) {
        return new Response<E>(true, null, body);
    }

    public static <E> Response Fail(String message) {
        return new Response<E>(false, message, null);
    }

}
