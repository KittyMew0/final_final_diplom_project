package com.example.NoteApp.repository;

import com.example.NoteApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс репозитория для сущностей Пользователь.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по его имени пользователя.
     *
     * @param username имя пользователя
     * @return Optional, содержащий пользователя, если он найден, иначе пустой
     */
    Optional<User> findByUsername(String username);
}
