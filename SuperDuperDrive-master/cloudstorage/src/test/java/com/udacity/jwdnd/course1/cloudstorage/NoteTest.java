package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NoteTest extends CloudStorageApplicationTests{

    private static String noteTitle = " test title";
    private static String noteDescription = " test description";
    private static Integer noteId= 111;

    @Test
    public void noteCreationTest() {
        WebDriverWait wait = new WebDriverWait (driver, 30);
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        //login
        driver.get("http://localhost:" + this.port + "/login");
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.sendKeys(userName);
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("submit-button"));
        loginButton.click();
        Assertions.assertEquals("Home", driver.getTitle());

        //added note
        WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", notesTab);
        wait.withTimeout(Duration.ofSeconds(30));
        WebElement newNote = driver.findElement(By.id("btnAddNewNote"));
        wait.until(ExpectedConditions.elementToBeClickable(newNote)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).sendKeys(noteTitle);
        WebElement notedescription = driver.findElement(By.id("note-description"));
        notedescription.sendKeys(noteDescription);
        WebElement savechanges = driver.findElement(By.id("btnSaveChanges"));
        savechanges.click();
        Assertions.assertEquals("Result", driver.getTitle());

        //check for note
        driver.get("http://localhost:" + this.port + "/home");
        notesTab = driver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", notesTab);
        WebElement notesTable = driver.findElement(By.id("userTable"));
        List<WebElement> notesList = notesTable.findElements(By.tagName("th"));
        Boolean created = false;
        for (int i=0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            if (element.getAttribute("innerHTML").equals(noteTitle)) {
                created = true;
                break;
            }
        }
        Assertions.assertTrue(created);
    }

    @Test
    public void noteUpdationTest() {
        WebDriverWait wait = new WebDriverWait (driver, 30);
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        String newNoteTitle = "new note title";
        String newNoteDescription = "new note Description";
        //login
        driver.get("http://localhost:" + this.port + "/login");
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.sendKeys(userName);
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("submit-button"));
        loginButton.click();
        Assertions.assertEquals("Home", driver.getTitle());

        //update note
        WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", notesTab);
        WebElement notesTable = driver.findElement(By.id("userTable"));
        List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
        WebElement editElement = null;
        for (int i = 0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            editElement = element.findElement(By.id("btnEditNote"));
            if (editElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
        WebElement notetitle = driver.findElement(By.id("note-title"));
        wait.until(ExpectedConditions.elementToBeClickable(notetitle));
        notetitle.clear();
        notetitle.sendKeys(newNoteTitle);
        WebElement notedescription = driver.findElement(By.id("note-description"));
        wait.until(ExpectedConditions.elementToBeClickable(notedescription));
        notetitle.clear();
        notetitle.sendKeys(newNoteDescription);
        WebElement savechanges = driver.findElement(By.id("btnSaveChanges"));
        savechanges.click();
        Assertions.assertEquals("Result", driver.getTitle());

        //check the updated note
        driver.get("http://localhost:" + this.port + "/home");
        notesTab = driver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", notesTab);
        notesTable = driver.findElement(By.id("userTable"));
        notesList = notesTable.findElements(By.tagName("th"));
        Boolean edited = false;
        for (int i = 0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            if (element.getAttribute("innerHTML").equals(newNoteTitle)||element.getAttribute("innerHTML").equals(newNoteDescription)) {
                edited = true;
                break;
            }
        }
        Assertions.assertTrue(edited);
    }

    @Test
    public void noteDeletionTest() {
        WebDriverWait wait = new WebDriverWait (driver, 30);
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        //login
        driver.get("http://localhost:" + this.port + "/login");
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.sendKeys(userName);
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("submit-button"));
        loginButton.click();
        Assertions.assertEquals("Home", driver.getTitle());

        WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", notesTab);
        WebElement notesTable = driver.findElement(By.id("userTable"));
        List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
        WebElement deleteElement = null;
        for (int i = 0; i < notesList.size(); i++) {
            WebElement element = notesList.get(i);
            deleteElement = element.findElement(By.id("ancDeleteNote"));
            if (deleteElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
        Assertions.assertEquals("Result", driver.getTitle());
    }

}
