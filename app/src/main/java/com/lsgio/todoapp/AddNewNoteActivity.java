package com.lsgio.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class AddNewNoteActivity extends AppCompatActivity {

    private TextInputEditText mTitleInputEditText;
    private TextInputEditText mDescriptionInputEditText;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        mActionBar = getSupportActionBar();
        mTitleInputEditText = findViewById(R.id.edittext_note_title);
        mDescriptionInputEditText = findViewById(R.id.edittext_note_description);

        if(mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_close);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                requestSaveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestSaveNote() {
        String title = mTitleInputEditText.getText().toString();
        String description = mDescriptionInputEditText.getText().toString();
        int priority = 1;

        Intent saveNoteIntent = new Intent();
        saveNoteIntent.putExtra("EXTRA_NOTE_TITLE", title);
        saveNoteIntent.putExtra("EXTRA_NOTE_DESCRIPTION", description);
        saveNoteIntent.putExtra("EXTRA_NOTE_PRIORITY", priority);

        setResult(RESULT_OK, saveNoteIntent);
        finish();
    }
}