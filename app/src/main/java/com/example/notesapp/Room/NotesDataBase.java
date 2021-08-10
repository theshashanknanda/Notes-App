package com.example.notesapp.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.Entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class
NotesDataBase extends RoomDatabase {

    public abstract NotesDao dao();
    public static NotesDataBase INSTANCE;

    public static NotesDataBase getNotesDataBaseInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesDataBase.class, "Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}

