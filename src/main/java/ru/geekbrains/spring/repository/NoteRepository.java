package ru.geekbrains.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
