package com.example.notesapp.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.Entity.Note;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM notes_database")
    public LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes_database ORDER BY notes_priority DESC")
    public LiveData<List<Note>> getSortedNotes();

    @Insert
    public void insert(Note... notes);

    @Query("DELETE FROM notes_database WHERE id=:id")
    public void delete(int id);

    @Update
    public void update(Note note);
}

