package com.lsgio.todoapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "priority")
    private int mPriority;

    @ColumnInfo(name = "creation_date")
    private String mCreationDate;

    public Note(String title, String description, int priority, String creationDate) {
        this.mTitle = title;
        this.mDescription = description;
        this.mPriority = priority;
        this.mCreationDate = creationDate;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getPriority() {
        return mPriority;
    }

    public String getCreationDate() {
        return mCreationDate;
    }

}
