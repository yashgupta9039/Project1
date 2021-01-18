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

public class CredentialsTest extends CloudStorageApplicationTests{

    private static String credURL = "example.com";

    @Test
    public void credentialCreationTest() {
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

        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", credTab);
        wait.withTimeout(Duration.ofSeconds(30));
        WebElement newCred = driver.findElement(By.id("btnAddNewCredential"));
        wait.until(ExpectedConditions.elementToBeClickable(newCred)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).sendKeys(credURL);
        WebElement credUsername = driver.findElement(By.id("credential-username"));
        credUsername.sendKeys(userName);
        WebElement credPassword = driver.findElement(By.id("credential-password"));
        credPassword.sendKeys(password);
        WebElement submit = driver.findElement(By.id("btnCredentialSaveChanges"));
        submit.click();
        Assertions.assertEquals("Result", driver.getTitle());

        //check for credential
        driver.get("http://localhost:" + this.port + "/home");
        credTab = driver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", credTab);
        WebElement credsTable = driver.findElement(By.id("credentialTable"));
        List<WebElement> credsList = credsTable.findElements(By.tagName("td"));
        Boolean created = false;
        for (int i=0; i < credsList.size(); i++) {
            WebElement element = credsList.get(i);
            if (element.getAttribute("innerHTML").equals(userName)) {
                created = true;
                break;
            }
        }
        Assertions.assertTrue(created);
    }

    @Test
    public void credentialUpdationTest() {
        WebDriverWait wait = new WebDriverWait (driver, 30);
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        String newCredUsername = "newUser";
        //login
        driver.get("http://localhost:" + this.port + "/login");
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.sendKeys(userName);
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("submit-button"));
        loginButton.click();
        Assertions.assertEquals("Home", driver.getTitle());

        //update credential
        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", credTab);
        WebElement credsTable = driver.findElement(By.id("credentialTable"));
        List<WebElement> credsList = credsTable.findElements(By.tagName("td"));
        WebElement editElement = null;
        for (int i = 0; i < credsList.size(); i++) {
            WebElement element = credsList.get(i);
            editElement = element.findElement(By.id("btnEditCredential"));
            if (editElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
        WebElement credUsername = driver.findElement(By.id("credential-username"));
        wait.until(ExpectedConditions.elementToBeClickable(credUsername));
        credUsername.clear();
        credUsername.sendKeys(newCredUsername);
        WebElement savechanges = driver.findElement(By.id("btnCredentialSaveChanges"));
        savechanges.click();
        Assertions.assertEquals("Result", driver.getTitle());

        //check the updated note
        driver.get("http://localhost:" + this.port + "/home");
        credTab = driver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", credTab);
        credsTable = driver.findElement(By.id("credentialTable"));
        credsList = credsTable.findElements(By.tagName("td"));
        Boolean edited = false;
        for (int i = 0; i < credsList.size(); i++) {
            WebElement element = credsList.get(i);
            if (element.getAttribute("innerHTML").equals(newCredUsername)) {
                edited = true;
                break;
            }
        }
        Assertions.assertTrue(edited);
    }

    @Test
    public void credentialDeletionTest() {
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

        WebElement credTab = driver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", credTab);
        WebElement credsTable = driver.findElement(By.id("credentialTable"));
        List<WebElement> credsList = credsTable.findElements(By.tagName("td"));
        WebElement deleteElement = null;
        for (int i = 0; i < credsList.size(); i++) {
            WebElement element = credsList.get(i);
            deleteElement = element.findElement(By.id("aDeleteCredential"));
            if (deleteElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
        Assertions.assertEquals("Result", driver.getTitle());
    }
}
