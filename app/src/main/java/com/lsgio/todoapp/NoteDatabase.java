package com.lsgio.todoapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase mInstance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, NoteDatabase.class, "note_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(mCallback)
                    .build();
        }
        return mInstance;
    }

    private static RoomDatabase.Callback mCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(mInstance).execute();

        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao mNoteDao;

        public PopulateDatabaseAsyncTask(NoteDatabase database) {
            this.mNoteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.insert(new Note("Sample", "This is a sample note and can be deleted", 1));
            return null;
        }
    }
}
