package com.task.tests;

import com.task.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @AfterSuite
    public void teardown() {
        DriverManager.quitDriver();
    }
}
