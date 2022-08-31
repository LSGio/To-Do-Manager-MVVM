package com.lsgio.todoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase mInstance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, NoteDatabase.class, "note_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }

    public abstract NoteDao noteDao();
}
