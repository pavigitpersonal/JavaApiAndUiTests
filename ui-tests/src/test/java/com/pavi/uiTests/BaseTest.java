package com.pavi.uiTests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties config;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        try {
            config = new Properties();
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // Teardown code for after the test suite runs goes here
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void beforeTest(String browser) {
        String isHeadless = config.getProperty("headless").toLowerCase();

        switch (browser) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (isHeadless.equalsIgnoreCase("true")) chromeOptions.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("toolkit.telemetry.shutdownPingSender.enabled", false);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                if (isHeadless.equalsIgnoreCase("true")) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "msedge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless.equalsIgnoreCase("true")) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
            }
            default -> System.out.println("Invalid browser specified");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.navigate().to(config.getProperty("web.app.url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                takeScreenshot(result.getName());
            } catch (IOException e) {
                System.out.println("Error taking screenshot: " + e.getMessage());
            }
        }
        driver.quit();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String screenshotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        File destination = new File("./build/allure-results/screenshots/" + fileName);
        FileUtils.copyFile(source, destination);
        try (InputStream inputStream = new FileInputStream(destination)) {
            Allure.addAttachment(fileName, inputStream);
        } catch (IOException e) {
            System.out.println("Error attaching screenshot to Allure report: " + e.getMessage());
        }
    }
}
