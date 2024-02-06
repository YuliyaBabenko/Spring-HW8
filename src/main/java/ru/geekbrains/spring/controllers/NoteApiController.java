package ru.geekbrains.spring.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.model.Note;
import ru.geekbrains.spring.services.NoteService;

import java.time.LocalDateTime;

/**
 * Контроллер API сервиса создания заметок
 * <li>Добавление заметки. (POST("/")</li>
 * <li>Просмотр всех заметок.(GET("/"))</li>
 * <li>Получение заметки по id. (GET("/{id}") )</li>
 * <li>Редактирование заметки по id.(PUT("/{id}") )</li>
 * <li>Удаление заметки по id.(DELETE("/{id}") )</li>
 */
@RestController
@RequiredArgsConstructor
@Log
public class NoteApiController {

    @Autowired
    private NoteService noteService;

    /**
     * Создать заметку
     *
     * @param note - заметка<br> {<br>"title":"наименование заметки",<br>
     *             "description":"тело заметки"<br>}<br>
     * @return - созданную заметку в JSON
     */
    @PostMapping("/")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        try{
            note.setDate(LocalDateTime.now());
            noteService.addNote(note);
            return ResponseEntity.ok(note);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(note);
        }

    }

    /**
     * Получить все заметки
     *
     * @return - JSON { заметка1, заметка2, ... заметка n}
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    /**
     * Получить заметку по id
     *
     * @param id - идентификатор заметки
     * @return - заметка JSON <br>
     * {<br>"id": [идентификатор],<br>
     * "title": "[наименование заметки]",<br>
     * "description": "[Тело заметки]",<br>
     * "date": [дата создания зщаметки]<br>}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        if (note == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(note);
    }

    /**
     * Изменить заметку
     *
     * @param id - идентификатор заметки
     * @param note - заметка JSON <br>
     *      {<br>"id": [идентификатор],<br>
     *      "title": "[наименование заметки]",<br>
     *      "description": "[Тело заметки]",<br>
     *      "date": [дата создания зщаметки]<br>}
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        noteService.updateNote(note);
        return ResponseEntity.ok().build();
    }

    /**
     * Удалить заметку
     *
     * @param id - идентификатор заметки
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }


}
