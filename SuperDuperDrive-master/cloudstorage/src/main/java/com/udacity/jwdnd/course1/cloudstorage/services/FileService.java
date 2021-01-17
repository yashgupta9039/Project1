package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public String[] getFileListings(Integer user_Id) {
        return fileMapper.getFileListings(user_Id);
    }

    public void addFile(MultipartFile multipartFile, String userName) throws IOException {

        byte[] file_Data = multipartFile.getBytes();

        String file_Name = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String file_Size = String.valueOf(multipartFile.getSize());
        Integer user_Id = userMapper.getUser(userName).getUser_Id();
        File file = new File(0, file_Name, contentType, file_Size, user_Id, file_Data);
        fileMapper.insert(file);
    }

    public File getFile(String file_Name) {
        return fileMapper.getFile(file_Name);
    }

    public void deleteFile(String file_Name) {
        fileMapper.deleteFile(file_Name);
    }
}