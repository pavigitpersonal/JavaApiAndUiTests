package com.pavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {
    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "head title")
    private WebElement title;

    @FindBy(id = "iterator_4_product_custom_more-info-button")
    private WebElement indiaTourMoreInfo;

    @FindBy(id = "searchtext_freetext_search_form")
    private WebElement searchBox;

    @FindBy(css = "button.nbf_button[type=\"submit\"]")
    private WebElement searchButton;

    @FindBy(id = "nbf_page_container")
    private WebElement nbfPageContainer;

    public String getPageTitle() {
        return driver.getTitle();
    }

    // Method to click on More Info for India 11 Days tour
    public void clickIndiaTourMoreInfo() {
        String selector = "iterator_4_product_custom_more-info-button";
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(selector)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", indiaTourMoreInfo);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(selector)));
        indiaTourMoreInfo.click();
    }
}
