package com.example.notesapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_database")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String notes_title;

    @ColumnInfo(name = "notes_subtitle")
    public String notes_subtitle;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_date")
    public String notes_date;

    @ColumnInfo(name = "notes_priority")
    public String notes_priority;
}
