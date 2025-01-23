package com.example.NoteApp.controller.notecontroller;

import com.example.NoteApp.model.Note;
import com.example.NoteApp.model.User;
import com.example.NoteApp.repository.NoteRepository;
import com.example.NoteApp.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления Заметками
 */

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

        /**
     * Конструктор для NoteController.
     *
     * @param noteRepository репозиторий для заметок
     * @param userRepository репозиторий для пользователей
     */
    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listNotes(org.springframework.ui.Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "notes";
    }

    /**
     * Добавляет новую заметку.
     *
     * @param note добавляемая заметка
     * @return сохраненная заметка
     */
    @GetMapping("/notes/add")
    public String showAddNoteForm(org.springframework.ui.Model model) {
        model.addAttribute("note", new Note());
        return "add-note";
    }

    @PostMapping("/notes")
    public String addNote(@ModelAttribute("note") Note note) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditNoteForm(@PathVariable Long id, org.springframework.ui.Model model) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        model.addAttribute("note", note);
        return "edit-note";
    }
        /**
     * Обновляет заметку по ее ID.
     *
     * @param id ID обновляемой заметки
     * @param updatedNote заметка с обновленной информацией
     * @return обновленная заметка
     */
        @PostMapping("/notes/update/{id}")
        public String updateNote(@PathVariable Long id, @ModelAttribute("note") Note updatedNote) {
            Note note = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Note not found"));

            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            noteRepository.save(note);
            return "redirect:/";
        }

        /**
     * Удаляет заметку по ее ID.
     *
     * @param id ID удаляемой заметки
     * @return нет содержимого, если заметка удалена, не найдено, если заметка не существует
     */
        @GetMapping("/notes/delete/{id}")
        public String deleteNote(@PathVariable Long id) {
            if (noteRepository.existsById(id)) {
                noteRepository.deleteById(id);
            }
            return "redirect:/";
        }
}