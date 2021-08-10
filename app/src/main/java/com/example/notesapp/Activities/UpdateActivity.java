package com.example.notesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.notesapp.Entity.Note;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    public String title, subtitle, notes, date, priority;
    public int id;

    public EditText titleEditText, subtitleEditText, notesEditText;
    public ImageView greenButton, yellowButton, redButton;
    public FloatingActionButton updateButton;
    public NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleEditText = findViewById(R.id.updateTitleEditText);
        subtitleEditText = findViewById(R.id.updateSubtitleEditText);
        notesEditText = findViewById(R.id.updateNotesEditText);
        greenButton = findViewById(R.id.updateGreenButton);
        yellowButton = findViewById(R.id.updateYellowButton);
        redButton = findViewById(R.id.updateRedButton);
        updateButton = findViewById(R.id.updateButton);
        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        notes = getIntent().getStringExtra("notes");
        date = getIntent().getStringExtra("date");
        priority = getIntent().getStringExtra("priority");
        id = getIntent().getIntExtra("id", 0);

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

        //hooking edit texts with existing data
        titleEditText.setText(title);
        subtitleEditText.setText(subtitle);
        notesEditText.setText(notes);

        if(priority.equals("1")){
            greenButton.setImageResource(R.drawable.ic_baseline_done_24);
            yellowButton.setImageResource(0);
            redButton.setImageResource(0);
        }

        if(priority.equals("2")){
            greenButton.setImageResource(0);
            yellowButton.setImageResource(R.drawable.ic_baseline_done_24);
            redButton.setImageResource(0);
        }

        if(priority.equals("3")){
            greenButton.setImageResource(0);
            yellowButton.setImageResource(0);
            redButton.setImageResource(R.drawable.ic_baseline_done_24);
        }

        updateButton.setOnClickListener(v -> {
            String updatedTitle, updatedSubTitle, updatedNotes;
            updatedTitle = titleEditText.getText().toString();
            updatedSubTitle = subtitleEditText.getText().toString();
            updatedNotes = notesEditText.getText().toString();

            if(!updatedTitle.isEmpty() && !updatedSubTitle.isEmpty() && !updatedNotes.isEmpty()){
                update(updatedTitle, updatedSubTitle, updatedNotes, date, priority, id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_delete:
                delete(id);
        }
        return true;
    }

    private void delete(int id){
        BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateActivity.this);

        View view = LayoutInflater.from(UpdateActivity.this).inflate(R.layout.delete_popup, (LinearLayout)findViewById(R.id.ll_1));

        Button yesButton = view.findViewById(R.id.yesButton);
        Button noButton = view.findViewById(R.id.noButton);

        yesButton.setOnClickListener(v -> {
            viewModel.deleteNote(id);
            sheetDialog.dismiss();;
            
            finish();;
        });

        noButton.setOnClickListener(v -> {
            sheetDialog.dismiss();;
        });

        sheetDialog.setContentView(view);
        sheetDialog.show();
    }

    private void update(String title, String subtitle, String notes, String date, String priority, int id){

        Note updatedNote = new Note();
        updatedNote.notes_title = title;
        updatedNote.notes_subtitle = subtitle;
        updatedNote.notes = notes;
        updatedNote.notes_date = date;
        updatedNote.notes_priority = priority;
        updatedNote.id = id;

        viewModel.updateNote(updatedNote);

        Toast.makeText(UpdateActivity.this, "Note updated succesfully", Toast.LENGTH_LONG).show();

        startActivity(new Intent(UpdateActivity.this, MainActivity.class));
    }
}
