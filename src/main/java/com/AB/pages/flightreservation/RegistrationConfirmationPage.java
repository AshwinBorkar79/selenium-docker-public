package com.AB.pages.flightreservation;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(xpath = "//a[text()='Go To Flights Search']")
    private WebElement searchFlights_Button;

    @FindBy(xpath = "//div[@class='col-md-6 mt-3']/p[text()='Congratulations ']/b")
    private WebElement firstName;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override                                   //notice the @Override annotation
    public boolean isPageLoaded()  {             //notice how the 'abstract' keyword is not used here

       this.wait.until(ExpectedConditions.visibilityOf(this.searchFlights_Button));
        return this.searchFlights_Button.isDisplayed();
    }

    public void searchFlights() {
        this.searchFlights_Button.click();
    }

    public String getFirstName() {
        return this.firstName.getText();
    }
}

