package com.pavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingDetailsPage {
    private final WebDriver driver;

    public BookingDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "head title")
    private WebElement title;

    @FindBy(css = "select[data-roomtype=\"Double \"]")
    private WebElement doubleRoomDropdown;

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    // Method to select double room for 2 adults
    public void selectDoubleRoomForTwoAdults() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[data-roomtype=\"Double \"]")));
        Select select = new Select(doubleRoomDropdown);
        select.selectByVisibleText("1");
        driver.findElement(By.cssSelector("div.nbf_fancy_nbf_tpl_pms_book_room")).click();
    }

    public void fillPassengerDetailsAndClickContinue() {
        // Passenger Details for 2 Adults
        String[] titles = {"Mr", "Mrs"};
        String[] firstNames = {"John", "Jane"};
        String[] lastNames = {"Doe", "Doe"};
        String[] datesOfBirthDay = {"3", "12"};
        String[] datesOfBirthMonth = {"March", "October"};
        String[] datesOfBirthYear = {"1987", "1993"};

        for (int i = 0; i < 2; i++) {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pax-a-title-1")));
            WebElement title = driver.findElement(By.id("pax-a-title-" + (i + 1)));
            Select titleDropdown = new Select(title);
            titleDropdown.selectByVisibleText(titles[i]);
            driver.findElement(By.id("pax-a-first-" + (i + 1))).sendKeys(firstNames[i]);
            driver.findElement(By.id("pax-a-last-" + (i + 1))).sendKeys(lastNames[i]);

            WebElement day = driver.findElement(By.id("pax-a-dobd-" + (i + 1)));
            Select dayDropdown = new Select(day);
            dayDropdown.selectByVisibleText(datesOfBirthDay[i]);

            WebElement month = driver.findElement(By.id("pax-a-dobm-" + (i + 1)));
            Select monthDropdown = new Select(month);
            monthDropdown.selectByVisibleText(datesOfBirthMonth[i]);

            WebElement year = driver.findElement(By.id("pax-a-doby-" + (i + 1)));
            Select yearDropdown = new Select(year);
            yearDropdown.selectByVisibleText(datesOfBirthYear[i]);
        }

        driver.findElement(By.id("contact-name")).clear();
        driver.findElement(By.id("contact-name")).sendKeys(firstNames[0] + lastNames[0]);
        driver.findElement(By.id("contact-mobile")).sendKeys("07519945637");
        driver.findElement(By.id("contact-email")).sendKeys("a@b.com");
        driver.findElement(By.id("contact-address1")).sendKeys("735 Burges Road");
        driver.findElement(By.id("contact-address2")).sendKeys("Stratford");
        driver.findElement(By.id("contact-city")).sendKeys("London");
        driver.findElement(By.id("contact-postcode")).sendKeys("E6 2BN");

        WebElement country = driver.findElement(By.id("contact-country"));
        Select countryDropdown = new Select(country);
        countryDropdown.selectByVisibleText("United Kingdom");

        WebElement hearAbout = driver.findElement(By.id("contact-hearabout"));
        Select hearAboutDropdown = new Select(hearAbout);
        hearAboutDropdown.selectByVisibleText("Email");

        driver.findElement(By.cssSelector("#paxform-select button[type=\"submit\"]")).click();
    }
}
