package com.pavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final String acceptCookiesSelector = "onetrust-accept-btn-handler";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "title")
    private WebElement title;

    @FindBy(id = acceptCookiesSelector)
    private WebElement acceptCookiesButton;

    @FindBy(id = "searchtext_freetext_search_form")
    private WebElement searchBox;

    @FindBy(css = "button.nbf_button[type=\"submit\"]")
    private WebElement searchButton;

    @FindBy(id = "nbf_page_container")
    private WebElement nbfPageContainer;

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    // Method to search for India
    public void searchForIndia() {
        searchBox.sendKeys("India");
        searchBox.submit();
    }

    public void clickAcceptCookies() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(acceptCookiesSelector)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(acceptCookiesSelector))).click();
    }

}
