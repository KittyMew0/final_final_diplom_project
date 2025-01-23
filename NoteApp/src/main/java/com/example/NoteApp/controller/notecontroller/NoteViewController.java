package com.example.NoteApp.controller.notecontroller;

import com.example.NoteApp.model.Note;
import com.example.NoteApp.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Класс контроллера для обработки представлений заметок.
 */
@Controller
@RequestMapping("/")
public class NoteViewController {

    private final NoteRepository noteRepository;

    /**
     * Конструктор для NoteViewController.
     *
     * @param noteRepository репозиторий для заметок
     */
    public NoteViewController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Отображает все заметки.
     *
     * @param model модель для добавления атрибутов
     * @return имя представления
     */
    @GetMapping
    public String viewNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }
}
