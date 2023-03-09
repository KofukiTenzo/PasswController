package com.projects.passwc.web;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {
    @ExceptionHandler(DuplicateUserException.class)
    public String handleNotFound() {
        return "error/duplicate";
    }
}
