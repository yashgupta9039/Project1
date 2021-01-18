package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FileTest extends CloudStorageApplicationTests{

    @Test
    public void fileUploadTest() throws InterruptedException {
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

        //upload file
        driver.findElement(By.id("fileUpload")).sendKeys("C:\\Users\\yashg\\Desktop\\mahakal.jpg");
        driver.findElement(By.id("submit-button1")).click();
        Assertions.assertEquals("Result", driver.getTitle());

        //check for file
        driver.get("http://localhost:" + this.port + "/home");
        WebElement fileTab = driver.findElement(By.id("fileUpload"));
        jse.executeScript("arguments[0].click()", fileTab);
        WebElement fileTable = driver.findElement(By.id("fileTable"));
        List<WebElement> fileList = fileTable.findElements(By.tagName("th"));
        Boolean created = false;
        for (int i=0; i < fileList.size(); i++) {
            WebElement element = fileList.get(i);
            if (element.getAttribute("innerHTML").equals("File Name")) {
                created = true;
                break;
            }
        }
        Assertions.assertTrue(created);
    }


    @Test
    public void fileDeletionTest() {
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

        WebElement fileTab = driver.findElement(By.id("nav-files-tab"));
        jse.executeScript("arguments[0].click()", fileTab);
        WebElement fileTable = driver.findElement(By.id("fileTable"));
        List<WebElement> filesList = fileTable.findElements(By.tagName("td"));
        WebElement deleteElement = null;
        for (int i = 0; i < filesList.size(); i++) {
            WebElement element = filesList.get(i);
            deleteElement = element.findElement(By.id("fileDeleteNote"));
            if (deleteElement != null){
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
        Assertions.assertEquals("Home", driver.getTitle());
    }

}
