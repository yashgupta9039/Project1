package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(
            FileService fileService, UserService userService, NoteService noteService,
            CredentialService credentialService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHomePage(
            Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
            @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential,
            Model model) {
        Integer user_Id = getUserId(authentication);
        model.addAttribute("files", this.fileService.getFileListings(user_Id));
        model.addAttribute("notes", noteService.getNoteListings(user_Id));
        model.addAttribute("credentials", credentialService.getCredentialListings(user_Id));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUser_Id();
    }

    @PostMapping
    public String newFile(
            Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
            @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) throws IOException, RuntimeException {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        Integer user_Id = user.getUser_Id();
        String[] fileListings = fileService.getFileListings(user_Id);
        MultipartFile multipartFile = newFile.getFile();
        String fileName = multipartFile.getOriginalFilename();
        boolean fileIsDuplicate = false;
        for (String fileListing: fileListings) {
            if (fileListing.equals(fileName)) {
                fileIsDuplicate = true;

                break;
            }
        }

        if (!fileIsDuplicate) {
            if(!fileName.isBlank()) {
                fileService.addFile(multipartFile, userName);
                model.addAttribute("result", "success");
            }
            else {
                model.addAttribute("result", "error");
                model.addAttribute("message", " please check file is been chosen or not");
            }
        } else {
            model.addAttribute("result", "error");
            model.addAttribute("message", "You have tried to add a duplicate file. or please check file is been chosen or not");
        }
        model.addAttribute("files", fileService.getFileListings(user_Id));

        return "result";
    }

    @GetMapping(
            value = "/get-file/{file_Name}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] getFile(@PathVariable String file_Name) {
        return fileService.getFile(file_Name).getFile_Data();
    }

    @GetMapping(value = "/delete-file/{file_Name}")
    public String deleteFile(
            Authentication authentication, @PathVariable String file_Name, @ModelAttribute("newFile") FileForm newFile,
            @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential,
            Model model) {
        fileService.deleteFile(file_Name);
        Integer user_Id = getUserId(authentication);
        model.addAttribute("files", fileService.getFileListings(user_Id));
        model.addAttribute("result", "success");

        return "result";
    }
}