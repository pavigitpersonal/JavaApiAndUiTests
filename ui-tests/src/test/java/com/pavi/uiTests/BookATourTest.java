package com.pavi.uiTests;

import com.pavi.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookATourTest extends BaseTest{
    @Test
    public void bookATourToIndia() {
        WebDriver driver = getDriver();

        HomePage homepage = new HomePage(driver);
        Assert.assertEquals(homepage.getPageTitle(), "Home Page | Mail Travel");

        homepage.searchForIndia();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        Assert.assertEquals(searchResultsPage.getPageTitle(), "Tour Search | Mail Travel");

        searchResultsPage.clickIndiaTourMoreInfo();

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertEquals(productDetailsPage.getPageTitle(), "India | Mail Travel");

        productDetailsPage.verifyIndiaTourDetails();
        productDetailsPage.selectDepartureDateAndClickBookOnline();

        BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);
        Assert.assertEquals(bookingDetailsPage.getPageTitle(), "Now Booking India | Mail Travel");

        bookingDetailsPage.selectDoubleRoomForTwoAdults();
        bookingDetailsPage.fillPassengerDetailsAndClickContinue();

        PaymentDetailsPage paymentDetailsPage = new PaymentDetailsPage(driver);
        Assert.assertEquals(paymentDetailsPage.getPageTitle(), "Payment Details");
        Assert.assertEquals(paymentDetailsPage.basketTotal(), "£4,198");
    }

}
