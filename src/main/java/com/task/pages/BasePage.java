package com.task.pages;

import com.task.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaderPresent() {
        List<WebElement> loader = driver.findElements(By.cssSelector("div[class*='loading-bar']"));
        return loader.size() > 0;
    }
}
