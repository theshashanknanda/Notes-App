package com.example.notesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Activities.MainActivity;
import com.example.notesapp.Activities.UpdateActivity;
import com.example.notesapp.Entity.Note;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    public Context context;
    public List<Note> noteList;

    public NotesAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
    }

    public void searchNotes(List<Note> updateList){
        this.noteList = updateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new NotesViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.title.setText(note.notes_title);
        holder.subtitle.setText(note.notes_subtitle);
        holder.notes.setText(note.notes);
        holder.date.setText(note.notes_date);

        if(note.notes_priority.equals("1")){
            holder.view.setBackgroundResource(R.drawable.circle_green);
        }

        if(note.notes_priority.equals("2")){
            holder.view.setBackgroundResource(R.drawable.circle_yellow);
        }

        if(note.notes_priority.equals("3")){
            holder.view.setBackgroundResource(R.drawable.circle_red);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), UpdateActivity.class);

            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notes_title);
            intent.putExtra("subtitle", note.notes_subtitle);
            intent.putExtra("notes", note.notes);
            intent.putExtra("date", note.notes_date);
            intent.putExtra("priority", note.notes_priority);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView title, subtitle, notes, date;
        public NotesViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            view = itemView.findViewById(R.id.view);
            title = itemView.findViewById(R.id.titleID);
            subtitle = itemView.findViewById(R.id.subtitleID);
            notes = itemView.findViewById(R.id.notesID);
            date = itemView.findViewById(R.id.dateID);
        }
    }
}
