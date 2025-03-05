package com.task.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.pages.DropDownResultsModal;
import com.task.pages.HomePage;
import com.task.pages.LoginModal;
import com.task.pages.ProductPage;
import com.task.utils.ConfigReader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TerminalXTest extends BaseTest {

    public static final String HOMEPAGE_URL = "https://www.terminalx.com";
    public static final String SEARCH_FOR = "hello";
    public static final int RESULT_TO_SELECT = 2;
    private static final String EXPECTED_PRICE_FONT_SIZE = "1.8rem";
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json";

    HomePage homePage;
    DropDownResultsModal dropdown;
    ProductPage productPage;
    @Test(priority = 1)
    public void navigateToHomePage() {
        System.out.println("Navigating to home page...");
        driver.get(HOMEPAGE_URL);
        homePage = new HomePage();
        Assert.assertTrue(homePage.isBannerDisplayed(), "Failed to navigate to home page");
    }

    @Test(priority = 2, dependsOnMethods = "navigateToHomePage")
    public void login() throws IOException {
        System.out.println("Navigated successfully.\nTrying to log in");
        Credentials credentials = getRandomCredentials();
        String username = credentials.getUsername();
        String userpass = credentials.getPassword();

        System.out.println("username = " + username + ", password = " + userpass);

        LoginModal loginModal = homePage.clickLogin();
        loginModal.enterEmail(username);
        loginModal.enterPassword(userpass);
        loginModal.clickSubmit();

        Assert.assertFalse(homePage.getLoggedUsername().isEmpty(), "Failed to log in");
    }

    @Test(priority = 3)
    public void checkDropDownResultsAllContainsHelloKitty() throws InterruptedException {
        System.out.println("Logged in successfully\nSearching for a product...");
        homePage.clickSearchButton();
        dropdown = homePage.enterSearchText(SEARCH_FOR);

        int productsAmount = dropdown.getResultsNamesSize();
        int pricesAmount = dropdown.getResultsPricesSize();
        Assert.assertEquals(productsAmount, pricesAmount, "products amount and prices amount are not the same: " + productsAmount + " products, " + pricesAmount + " prices");

        if (dropdown.getResultsNamesSize() > 0) {
            boolean areAllResultsContainHelloKitty = true;
            for (int i = 0; i < productsAmount; i++) {
                if (!dropdown.getResultName(i).contains("hello kitty")) {
                    areAllResultsContainHelloKitty = false;
                    break;
                }
            }

            System.out.println("All results contain 'hello kitty': " + areAllResultsContainHelloKitty);
        }

    }

    @Test(priority = 4)
    public void checkDropDownResultsAscendingPrices() {
        List<String> allPricesStr = dropdown.getAllPrices();
        double prevPrice = priceWithoutCurrency(allPricesStr.get(0));
        double currPrice;
        boolean isAscending = true;
        for (int i = 1; i < allPricesStr.size(); i++) {
            currPrice = priceWithoutCurrency(allPricesStr.get(i));
            if (currPrice < prevPrice) {
                isAscending = false;
                break;
            }

            prevPrice = currPrice;
        }

        System.out.println("All prices in dropdown are ascending: " + isAscending);
    }

    @Test(priority = 5)
    public void enterResultPage() throws InterruptedException {
        System.out.println("Clicking on a result...");
        productPage = dropdown.clickResult(2);
        Assert.assertEquals(productPage.getExpectedName(), productPage.getProductName(), "Incorrect product page");
    }

    @Test(priority = 6)
    public void checkProductPriceExistence() {
        System.out.println("Checking for existence of price...");
        int pricesAmount = productPage.getPricesSize();
        for (int i = 0; i < pricesAmount; i++) {
            String priceText = productPage.getPriceText(i);
            Assert.assertFalse(priceText.isEmpty(), "Price's field is empty (price index = " + i + ")");
        }
        System.out.println("Exist");
    }

    @Test(priority = 7)
    public void checkProductPriceFontSize() {
        System.out.println("Checking font size is 1.8rem...");
        int pricesAmount = productPage.getPricesSize();
        for (int i = 0; i < pricesAmount; i++) {
            String fontsize = productPage.getPriceFontSize(i);
            Assert.assertEquals(EXPECTED_PRICE_FONT_SIZE, fontsize, "Unexpected font size: ");
        }
        System.out.println("Validated");
    }

    private double priceWithoutCurrency(String price) {
        return Double.valueOf(price.replace("₪", "").trim());
    }


    private Credentials getRandomCredentials() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File credentialsFile = new File(CREDENTIALS_FILE_PATH);

        List<Credentials> credentialsList = objectMapper.readValue(credentialsFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Credentials.class));

        int randIndex = new Random().nextInt(credentialsList.size());
        return credentialsList.get(randIndex);
    }
}

class Credentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}