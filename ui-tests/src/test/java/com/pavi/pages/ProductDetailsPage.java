package com.pavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductDetailsPage {

    private final WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "head title")
    private WebElement title;

    @FindBy(css = "#price-pin_price_non_newmarket span.ibecurr")
    private WebElement indiaTourPrice;

    @FindBy(id = "price-pin_riviera-days-num")
    private WebElement indiaTourDuration;

    @FindBy(id = "supplier-phone")
    private WebElement indiaTourTelephone;

    @FindBy(linkText = "Dates & Prices")
    private WebElement datesAndPrices;

    @FindBy(css = "input[type=\"radio\"][name=\"seldate\"]")
    private WebElement departureDate;

    @FindBy(css = "#passenger-dropdown select[name=\"numAdults\"]")
    private WebElement adultsDropdown;

    @FindBy(id = "book-button")
    private WebElement bookOnlineButton;

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void verifyIndiaTourDetails() {
        Assert.assertTrue(indiaTourPrice.isDisplayed());
        Assert.assertTrue(indiaTourDuration.isDisplayed());
        Assert.assertTrue(indiaTourTelephone.isDisplayed());
    }

    public void selectDepartureDateAndClickBookOnline() {
        datesAndPrices.click();

        String selector = "input[type=\"radio\"][name=\"seldate\"]";
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        departureDate.click();
        bookOnlineButton.click();
    }
}
