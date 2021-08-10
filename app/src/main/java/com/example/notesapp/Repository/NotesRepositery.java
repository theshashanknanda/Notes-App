package com.example.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.Room.NotesDao;
import com.example.notesapp.Room.NotesDataBase;

import java.util.List;

public class NotesRepositery {

    public NotesDao notesDao;
    public LiveData<List<Note>> getAllNotes;
    public LiveData<List<Note>> getSortedNotes;

    public NotesRepositery(Application application){
        NotesDataBase dataBase = NotesDataBase.getNotesDataBaseInstance(application);
        notesDao = dataBase.dao();
        getAllNotes = notesDao.getAllNotes();
        getSortedNotes = notesDao.getSortedNotes();
    }

    public void insertNotes(Note note){
        notesDao.insert(note);
    }

    public void deleteNotes(int id){
        notesDao.delete(id);
    }

    public void updateNotes(Note note){
        notesDao.update(note);
    }
}
