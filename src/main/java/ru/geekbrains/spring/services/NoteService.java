package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.aspects.TrackUserAction;
import ru.geekbrains.spring.model.Note;
import ru.geekbrains.spring.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @TrackUserAction
    public void addNote(Note note) throws RuntimeException {
        noteRepository.save(note);
    }

    @TrackUserAction
    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        noteRepository.findAll().iterator().forEachRemaining(noteList::add);
        return noteList;
    }

    @TrackUserAction
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @TrackUserAction
    public void updateNote(Note note) {
        noteRepository.save(note);
    }

    @TrackUserAction
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
