package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer user_Id;

    public Note(Integer noteId, String noteTitle, String noteDescription, Integer user_Id) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.user_Id = user_Id;
    }

    public Note(String noteTitle, String noteDescription) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return noteId;
    }


    public String getNoteTitle() {
        return noteTitle;
    }


    public String getNoteDescription() {
        return noteDescription;
    }


    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }
}