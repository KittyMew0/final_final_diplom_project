package com.example.NoteApp.repository;

import com.example.NoteApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс репозитория для сущностей Заметки.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Находит заметку по ее ID.
     *
     * @param id ID заметки
     * @return Optional, содержащий заметку, если она найдена, иначе пустой
     */
    Optional<Note> findById(Long id);
}
