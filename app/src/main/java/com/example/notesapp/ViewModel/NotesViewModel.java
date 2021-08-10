package com.example.notesapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.Repository.NotesRepositery;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepositery repositery;
    public LiveData<List<Note>> getAllNotes;
    public LiveData<List<Note>> getSortedNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repositery = new NotesRepositery(application);
        getAllNotes = repositery.getAllNotes;
        getSortedNotes = repositery.getSortedNotes;
    }

    public void insertNote(Note note){
        repositery.insertNotes(note);
    }

    public void deleteNote(int id){
        repositery.deleteNotes(id);
    }

    public void updateNote(Note note){
        repositery.updateNotes(note);
    }
}
