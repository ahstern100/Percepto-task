package com.task.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    private String expectedName;
    public ProductPage(String expectedName) {
        this.expectedName = expectedName;
    }

    public String getExpectedName() {
        return expectedName;
    }

    @FindBy(css = "div[class*='bold'] div[data-test-id*='qa-price']")
    private List<WebElement> prices;

    @FindBy(css = "h1[data-test-id='qa-pdp-name']")
    private WebElement productName;

    public int getPricesSize() {
        return prices.size();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getPriceText(int index) {
        return prices.get(index).getText();
    }

    public String getPriceFontSize(int index) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement price = prices.get(index);
        String fontSize = (String) js.executeScript("return window.getComputedStyle(arguments[0], null).getPropertyValue('font-size');", price);
        return fontSize;
    }
}
