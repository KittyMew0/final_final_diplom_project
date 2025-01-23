package com.example.NoteApp;

import com.example.NoteApp.model.Note;
import com.example.NoteApp.model.User;
import com.example.NoteApp.repository.NoteRepository;
import com.example.NoteApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Компонент для инициализации данных.
 */
@Component
public class DataInitializer {

    /**
     * Инициализирует пользователей в базе данных.
     *
     * @param userRepository репозиторий для пользователей
     * @param passwordEncoder кодировщик паролей
     * @return бин CommandLineRunner для инициализации пользователей
     */
    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository,
                                       NoteRepository noteRepository,
                                       PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            userRepository.save(user);

            Note note = new Note();
            note.setTitle("Test Note");
            note.setContent("This is a test note");
            note.setUser(user);
            noteRepository.save(note);
        };
    }
}
