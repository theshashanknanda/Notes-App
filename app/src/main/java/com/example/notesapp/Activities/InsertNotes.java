package com.example.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertNotes extends AppCompatActivity {
    public ImageView greenButton;
    public ImageView yellowButton;
    public ImageView redButton;

    public EditText titleEditText;
    public EditText subtitleEditText;
    public EditText notesEditText;

    public FloatingActionButton doneButton;

    public NotesViewModel viewModel;

    public String priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);

        greenButton = findViewById(R.id.greenButton);
        yellowButton = findViewById(R.id.yellowButton);
        redButton = findViewById(R.id.redButton);
        titleEditText = findViewById(R.id.titleEditText);
        subtitleEditText = findViewById(R.id.subtitleEditText);
        notesEditText = findViewById(R.id.notesEditText);
        doneButton = findViewById(R.id.addButton);
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        greenButton.setOnClickListener(v -> {
            greenButton.setImageResource(R.drawable.ic_baseline_done_24);
            yellowButton.setImageResource(0);
            redButton.setImageResource(0);

            priority = "1";
        });

            yellowButton.setOnClickListener(v -> {
            greenButton.setImageResource(0);
            yellowButton.setImageResource(R.drawable.ic_baseline_done_24);
            redButton.setImageResource(0);

            priority = "2";
        });

        redButton.setOnClickListener(v -> {
            greenButton.setImageResource(0);
            yellowButton.setImageResource(0);
            redButton.setImageResource(R.drawable.ic_baseline_done_24);

            priority = "3";
        });

        doneButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String subtitle = subtitleEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate= formatter.format(date);

            if(!title.isEmpty() && !subtitle.isEmpty() && !notes.isEmpty()){
                add(title, subtitle, notes, strDate, priority);
            }
            else
            {
                Toast.makeText(InsertNotes.this, "Empty Blanks", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void add(String title, String subtitle, String notes, String strDate, String priority) {
        Note note = new Note();
        note.notes_title = title;
        note.notes_subtitle = subtitle;
        note.notes = notes;
        note.notes_date = strDate;
        note.notes_priority = priority;

        viewModel.insertNote(note);

        Toast.makeText(InsertNotes.this, "Note added", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InsertNotes.this, MainActivity.class));
    }
}
