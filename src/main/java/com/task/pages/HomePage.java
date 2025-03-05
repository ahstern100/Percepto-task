package com.task.pages;

import com.task.locators.HomePageLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    @FindBy(css = "button[data-test-id='qa-header-login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test-id='qa-header-search-button']")
    private WebElement searchButton;

    @FindBy(css = "div[class*='header-logo-wrapper']")
    private WebElement banner;



    public LoginModal clickLogin() {
        loginButton.click();
        return new LoginModal();
    }

    public String getLoggedUsername() {
        List<WebElement> loggedUserSpans = driver.findElements(HomePageLocators.loggedUsername);
        if (loggedUserSpans.size() >= 2) {
            return loggedUserSpans.get(1).getText();
        }

        return null;
    }

    public void clickSearchButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> !isLoaderPresent());
//        wait.until(ExpectedConditions.visibilityOf(searchButton));
//        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public DropDownResultsModal enterSearchText(String searchText) {
        WebElement searchBox = driver.findElement(HomePageLocators.searchBox);
        if (!searchBox.isDisplayed()) {
            throw new NoSuchElementException("search box does not exist");
        }

        searchBox.sendKeys(searchText);

        return new DropDownResultsModal();

    }

    public boolean isBannerDisplayed() {
        return banner.isDisplayed();
    }

}
