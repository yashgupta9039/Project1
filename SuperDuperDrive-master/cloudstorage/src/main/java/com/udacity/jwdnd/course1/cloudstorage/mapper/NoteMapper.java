package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE user_Id = #{user_Id}")
    Note[] getNotesForUser(Integer user_Id);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, user_Id) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{user_Id})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Select("SELECT * FROM NOTES")
    Note[] getNoteListings();

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getNote(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET noteTitle = #{title}, noteDescription = #{description} WHERE noteId = #{noteId}")
    void updateNote(Integer noteId, String title, String description);

}