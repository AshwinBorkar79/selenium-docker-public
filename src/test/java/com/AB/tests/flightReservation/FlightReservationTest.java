package com.AB.tests.flightReservation;

import com.AB.pages.flightreservation.*;
import com.AB.tests.AbstractTest;
import com.AB.tests.flightReservation.model.FlightReservationTestData;
import com.AB.util.Config;
import com.AB.util.Constants;
import com.AB.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTest extends AbstractTest {


    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")

    public void setParameters(String testDataPath) throws IOException {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest()
    {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.getProperty(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isPageLoaded());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName()); //note how testData.getFirstName() is not used, instead directly testData.firstName() is used
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterUserAddress(testData.street(), testData.city(), testData.zip());

        registrationPage.registerUser();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest()
    {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isPageLoaded());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.searchFlights();
    }


    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        flightSearchPage.isPageLoaded();

        flightSearchPage.selectPassengers(testData.passengerCount());
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest()
    {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        flightSelectionPage.isPageLoaded();

        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isPageLoaded());

        Assert.assertEquals(flightConfirmationPage.getTotalPrice(), testData.expectedPrice());
    }

}
