package com.pavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentDetailsPage {

    private final WebDriver driver;
    private final String basketTotalSelector = ".nbf_basket_total .ibecurr";
    private final String basketSelector = "nbf_basket";

    public PaymentDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "head title")
    private WebElement title;

    @FindBy(id = basketSelector)
    private WebElement basketContainer;
    @FindBy(css = basketTotalSelector)
    private WebElement basketTotal;

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(basketSelector)));
        return this.driver.getTitle();
    }
    public String basketTotal() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(basketTotalSelector)));
        return basketTotal.getText();
    }
}
