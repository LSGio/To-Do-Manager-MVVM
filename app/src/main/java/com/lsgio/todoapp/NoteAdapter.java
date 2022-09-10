package com.lsgio.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> mNotes = new ArrayList<>();

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextview;
        private TextView mDescriptionTextview;
        private TextView mPriorityTextview;
        private TextView mCreationDateTextview;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextview = itemView.findViewById(R.id.textview_title);
            mDescriptionTextview = itemView.findViewById(R.id.textview_description);
            mPriorityTextview = itemView.findViewById(R.id.textview_priority);
            mCreationDateTextview = itemView.findViewById(R.id.textview_creation_date);
        }
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = mNotes.get(position);
        holder.mTitleTextview.setText(currentNote.getTitle());
        holder.mDescriptionTextview.setText(currentNote.getDescription());
        holder.mPriorityTextview.setText(String.valueOf(currentNote.getPriority()));
        holder.mCreationDateTextview.setText(currentNote.getCreationDate());
    }

    @Override
    public int getItemCount() {
        return this.mNotes.size();
    }

    public void setNotes(List<Note> notes) {
        this.mNotes = notes;
        notifyDataSetChanged();
    }

}
