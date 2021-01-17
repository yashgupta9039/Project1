package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private int file_Id;
    private String file_Name;
    private String contentType;
    private String file_Size;
    private Integer user_Id;
    private byte[] file_Data;

    public File(int file_Id, String file_Name, String contentType, String file_Size, Integer user_Id, byte[] file_Data) {
        this.file_Id = file_Id;
        this.file_Name = file_Name;
        this.contentType = contentType;
        this.file_Size = file_Size;
        this.user_Id = user_Id;
        this.file_Data = file_Data;
    }

    public int getFile_Id() {
        return file_Id;
    }

    public void setFile_Id(int file_Id) {
        this.file_Id = file_Id;
    }

    public String getFile_Name() {
        return file_Name;
    }

    public void setFile_Name(String file_Name) {
        this.file_Name = file_Name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFile_Size() {
        return file_Size;
    }

    public void setFile_Size(String file_Size) {
        this.file_Size = file_Size;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public byte[] getFile_Data() {
        return file_Data;
    }

    public void setFile_Data(byte[] file_Data) {
        this.file_Data = file_Data;
    }
}