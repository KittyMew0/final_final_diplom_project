package com.example.NoteApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс контроллера для обработки представлений входа.
 */
@Controller
public class LoginController {

    /**
     * Отображает страницу входа.
     *
     * @return имя представления для страницы входа
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
