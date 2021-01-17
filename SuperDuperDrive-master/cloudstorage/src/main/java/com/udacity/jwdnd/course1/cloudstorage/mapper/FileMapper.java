package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE file_name = #{file_Name}")
    File getFile(String file_Name);

    @Select("SELECT file_name FROM FILES WHERE user_id = #{user_Id}")
    String[] getFileListings(Integer user_Id);

    @Insert("INSERT INTO FILES (file_name, contenttype, file_size, user_id, file_data) " +
            "VALUES(#{file_Name}, #{contentType}, #{file_Size}, #{user_Id}, #{file_Data})")
    @Options(useGeneratedKeys = true, keyProperty = "file_Id")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE file_name = #{file_Name}")
    void deleteFile(String file_Name);
}