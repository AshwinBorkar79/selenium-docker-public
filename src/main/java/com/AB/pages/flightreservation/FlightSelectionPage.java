package com.AB.pages.flightreservation;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='radio' and @name='departure-flight']")
    private List <WebElement> departureFlightOptions;

    @FindBy(xpath = "//input[@type='radio' and @name='arrival-flight']")
    private List <WebElement> ariivalFlightOptions;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {

        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();
    }

    public void selectFlights(){

        int random = ThreadLocalRandom.current().nextInt(0, this.departureFlightOptions.size()); //2nd param is exclusive i.e. it is < 2nd param
        this.departureFlightOptions.get(random).click();
        this.ariivalFlightOptions.get(random).click();
    }

    public void confirmFlights() {
        this.confirmFlightsButton.click();
    }
}
