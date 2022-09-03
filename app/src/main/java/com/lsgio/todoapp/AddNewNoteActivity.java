package com.lsgio.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewNoteActivity extends AppCompatActivity {

    private TextInputEditText mTitleInputEditText;
    private TextInputEditText mDescriptionInputEditText;
    private Slider mPrioritySlider;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        mActionBar = getSupportActionBar();
        mTitleInputEditText = findViewById(R.id.edittext_note_title);
        mDescriptionInputEditText = findViewById(R.id.edittext_note_description);
        mPrioritySlider = findViewById(R.id.slider_priority);

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
        final int id = item.getItemId();
        if (id == R.id.menu_item_save) {
            requestSaveNote();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    private void requestSaveNote() {
        String title = mTitleInputEditText.getText().toString();
        String description = mDescriptionInputEditText.getText().toString();
        int priority = (int) mPrioritySlider.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Cannot have empty title or description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent saveNoteIntent = new Intent();
        saveNoteIntent.putExtra(Constants.EXTRA_NOTE_TITLE, title);
        saveNoteIntent.putExtra(Constants.EXTRA_NOTE_DESCRIPTION, description);
        saveNoteIntent.putExtra(Constants.EXTRA_NOTE_PRIORITY, priority);

        setResult(RESULT_OK, saveNoteIntent);
        finish();
    }
}