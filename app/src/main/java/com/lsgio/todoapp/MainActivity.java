package com.lsgio.todoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_ADD_NEW_NOTE = 89461;

    private ActivityResultLauncher<Intent> mActivityResultLauncher;

    private NoteViewModel mNoteViewModel;
    private RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;

    private FloatingActionButton mAddNoteFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_notes);
        mAddNoteFloatingActionButton = findViewById(R.id.floatingactionbutton_add_note);

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteAdapter = new NoteAdapter();
        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> handleNoteAddResult(result));

        mRecyclerView.setAdapter(mNoteAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mNoteAdapter.setmNotes(notes);
            }
        });

        mAddNoteFloatingActionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingactionbutton_add_note) {
            Intent mAddNoteIntent = new Intent();
            mAddNoteIntent.setComponent(new ComponentName(getApplicationContext(), AddNewNoteActivity.class));
            mActivityResultLauncher.launch(mAddNoteIntent);
        }
    }

    public void handleNoteAddResult(ActivityResult result) {

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_CODE_ADD_NEW_NOTE) {
//            if(resultCode == RESULT_OK) {
//                final String title = data.getStringExtra(Constants.EXTRA_NOTE_TITLE);
//                final String description = data.getStringExtra(Constants.EXTRA_NOTE_DESCRIPTION);
//                final int priority = data.getIntExtra(Constants.EXTRA_NOTE_PRIORITY,1);
//                Note note = new Note(title, description, priority);
//                mNoteViewModel.insert(note);
//                Toast.makeText(this, getString(R.string.prompt_note_saved), Toast.LENGTH_SHORT).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, getString(R.string.prompt_note_not_saved), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}