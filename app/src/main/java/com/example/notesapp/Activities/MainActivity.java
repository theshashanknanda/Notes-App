package com.example.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notesapp.Adapters.NotesAdapter;
import com.example.notesapp.Entity.Note;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public FloatingActionButton fab;
    public RecyclerView recyclerView;
    public NotesViewModel viewModel;
    public NotesAdapter adapter;
    public TextView nofilterTextView;
    public TextView filterTextView;

    public ArrayList<Note> filterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        recyclerView = findViewById(R.id.notesRecyclerView);
        nofilterTextView = findViewById(R.id.noFilterTextView);
        filterTextView = findViewById(R.id.filterTextView);
        filterList = new ArrayList<>();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNotes.class));
                finish();
            }
        });

        viewModel.getAllNotes.observe(this, notes -> {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            recyclerView.setAdapter(adapter);

            filterList = (ArrayList<Note>) notes;
        });

        nofilterTextView.setOnClickListener(v -> {
            nofilterTextView.setBackgroundResource(R.drawable.sort_bg_1);
            nofilterTextView.setTextColor(getResources().getColor(R.color.white));

            filterTextView.setBackgroundResource(R.drawable.sort_bg_0);
            filterTextView.setTextColor(getResources().getColor(R.color.black));

            viewModel.getAllNotes.observe(this, notes -> {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                adapter = new NotesAdapter(MainActivity.this, notes);
                recyclerView.setAdapter(adapter);

                filterList = (ArrayList<Note>) notes;
            });
        });

        filterTextView.setOnClickListener(v -> {
            nofilterTextView.setBackgroundResource(R.drawable.sort_bg_0);
            nofilterTextView.setTextColor(getResources().getColor(R.color.black));

            filterTextView.setBackgroundResource(R.drawable.sort_bg_1);
            filterTextView.setTextColor(getResources().getColor(R.color.white));

            viewModel.getSortedNotes.observe(this, notes -> {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                adapter = new NotesAdapter(MainActivity.this, notes);
                recyclerView.setAdapter(adapter);
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Search here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Note> newList = new ArrayList<>();
                newList.clear();

                for(Note note: filterList)
                {
                    if(note.notes_title.contains(newText)){
                        newList.add(note);
                    }
                }
                adapter.searchNotes(newList);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
