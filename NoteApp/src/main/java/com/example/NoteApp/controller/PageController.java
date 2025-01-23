package com.example.NoteApp.controller;

import com.example.NoteApp.model.Note;
import com.example.NoteApp.model.User;
import com.example.NoteApp.repository.NoteRepository;
import com.example.NoteApp.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Класс контроллера для обработки просмотра страниц и операций с заметками.
 */
@Controller
@RequestMapping("/index")
public class PageController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    /**
     * Конструктор для PageController.
     *
     * @param noteRepository репозиторий для заметок
     * @param userRepository репозиторий для пользователей
     */
    public PageController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    /**
     * Отображает главную страницу индекса.
     *
     * @return имя представления для страницы индекса
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Выводит список всех заметок.
     *
     * @param model модель для добавления атрибутов
     * @return имя представления для списка заметок
     */
    @GetMapping("/index")
    public String listNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }

    /**
     * Отображает форму для добавления новой заметки.
     *
     * @param model модель для добавления атрибутов
     * @return имя представления для формы добавления заметки
     */
    @GetMapping("/notes/add")
    public String showAddNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "add-note";
    }

    /**
     * Добавляет новую заметку.
     *
     * @param note добавляемая заметка
     * @return URL перенаправления на список заметок
     */
    @PostMapping("/notes")
    public String addNote(@ModelAttribute("note") Note note) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/notes";
    }

    /**
     * Отображает форму для редактирования существующей заметки.
     *
     * @param id ID редактируемой заметки
     * @param model модель для добавления атрибутов
     * @return имя представления для формы редактирования заметки
     */
    @GetMapping("/notes/edit/{id}")
    public String showEditNoteForm(@PathVariable Long id, Model model) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        model.addAttribute("note", note);
        return "edit-note";
    }

    /**
     * Обновляет существующую заметку.
     *
     * @param id ID обновляемой заметки
     * @param updatedNote заметка с обновленной информацией
     * @return URL перенаправления на список заметок
     */
    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("note") Note updatedNote) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        noteRepository.save(note);
        return "redirect:/notes";
    }

    /**
     * Удаляет заметку по ее ID.
     *
     * @param id ID удаляемой заметки
     * @return URL перенаправления на список заметок
     */
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
        return "redirect:/notes";
    }
}
