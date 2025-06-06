package com.AB.pages.flightreservation;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FlightSearchPage extends AbstractPage {

    @FindBy(xpath = "//select[@id='passengers']")
    private WebElement passengers;

    @FindBy(xpath = "//button[text()='Search Flights']")
    private WebElement searchFlights_button;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchFlights_button));
        return this.searchFlights_button.isDisplayed();
    }


    public void selectPassengers(String numberOfPassengers) {
        Select select = new Select(this.passengers);
        select.selectByVisibleText(numberOfPassengers);
    }

    public void searchFlights() {
        this.searchFlights_button.click();

    }
}
