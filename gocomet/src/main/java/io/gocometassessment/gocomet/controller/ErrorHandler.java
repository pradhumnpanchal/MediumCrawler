package io.gocometassessment.gocomet.controller;

import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandler implements ErrorController {
    @RequestMapping("/error")
    public String error() {
        return "Bad Request";
    }
    @Override
    public String getErrorPath() {
        return "Bad Request";
    }
}
