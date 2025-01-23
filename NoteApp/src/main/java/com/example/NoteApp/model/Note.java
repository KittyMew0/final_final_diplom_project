package com.example.NoteApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс сущности, представляющий Заметку.
 */
@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Устанавливает метку времени создания перед сохранением сущности.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Получает заголовок заметки.
     *
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок заметки.
     *
     * @param title устанавливаемый заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получает содержимое заметки.
     *
     * @return содержимое
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает содержимое заметки.
     *
     * @param content устанавливаемое содержимое
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Получает пользователя, связанного с заметкой.
     *
     * @return пользователь
     */
    public User getUser() {
        return user;
    }

    /**
     * Устанавливает пользователя, связанного с заметкой.
     *
     * @param user устанавливаемый пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

}
