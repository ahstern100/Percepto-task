package com.task.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModal extends BasePage {

    @FindBy(id = "qa-login-email-input")
    private WebElement txtEmail;

    @FindBy(id = "qa-login-password-input")
    private WebElement txtPassword;

    @FindBy(css = "[data-test-id='qa-login-submit']")
    private WebElement btnSubmit;

    public void enterEmail(String emailAddress) {
        txtEmail.sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickSubmit() {
        btnSubmit.click();
    }
}
