package com.udacity.jwdnd.course1.cloudstorage.mapper;
/* Credit for this file goes to Udacity notes */
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT * FROM USERS WHERE user_id = #{user_Id}")
    User getUserById(Integer user_Id);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "user_Id")
    int insert(User user);
}