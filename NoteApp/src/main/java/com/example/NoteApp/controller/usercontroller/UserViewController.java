package com.example.NoteApp.controller.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс контроллера для обработки представлений пользователей.
 */
@Controller
public class UserViewController {

    /**
     * Отображает страницу регистрации.
     *
     * @return имя представления для страницы регистрации
     */
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }
}
