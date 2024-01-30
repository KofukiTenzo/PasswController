package com.projects.passwc.exceptions;

import com.projects.passwc.exceptions.DuplicateUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {
    @ExceptionHandler(DuplicateUserException.class)
    public String handleNotFound() {
        return "error/duplicate";
    }
}
