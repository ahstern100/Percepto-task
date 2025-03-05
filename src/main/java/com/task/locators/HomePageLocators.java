package com.task.locators;

import org.openqa.selenium.By;

public class HomePageLocators {
    public static final By loggedUsername = By.cssSelector("button[data-test-id='qa-header-profile-button'] span");
    public static final By searchBox = By.cssSelector("input[data-test-id='qa-search-box-input'");
//    public static final By searchButton = By.cssSelector("[data-test-id='qa-header-search-button']");
}
