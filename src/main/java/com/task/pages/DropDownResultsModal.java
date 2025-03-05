package com.task.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class DropDownResultsModal extends BasePage {
    @FindBy(css = "ul li.container_2pxt a[class*='nameLink']")
    private List<WebElement> dropdownResultsNames;

    @FindBy(css = "ul li.container_2pxt div[class*='price'] div[class*='bold']")
    private List<WebElement> dropdownResultsPrices;

    public String getResultName(int index) {
        return dropdownResultsNames.get(index).getText();
    }

    public int getResultsNamesSize() {
        return dropdownResultsNames.size();
    }

    public String getResultPrice(int index) {
        return dropdownResultsPrices.get(index).getText();
    }

    public List<String> getAllPrices() {
        List<String> allPrices = new ArrayList<>();
        for (WebElement price : dropdownResultsPrices) {
            allPrices.add(price.getText());
        }

        return allPrices;
    }

    public int getResultsPricesSize() {
        return dropdownResultsPrices.size();
    }

    public ProductPage clickResult(int index) throws InterruptedException {
        String productText = dropdownResultsNames.get(index).getText();
        dropdownResultsNames.get(index).click();
        return new ProductPage(productText);
    }
}
