package com.AB.pages.flightreservation;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(xpath = "//div[@class='col-md-6 mt-3']//form[@class='row g-3']//div[@class='card-body']/div[3]/div[1]")
    private WebElement TotalPriceLabel;

    @FindBy(xpath = "//div[@class='col-md-6 mt-3']//form[@class='row g-3']//div[@class='card-body']/div[3]/div[2]")
    private WebElement TotalPriceValue;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {

        this.wait.until(ExpectedConditions.visibilityOf(this.TotalPriceLabel));
        return this.TotalPriceLabel.isDisplayed();

    }

    public String getTotalPrice() {
        String totalPrice = this.TotalPriceLabel.getText();
        String totalPriceValue = this.TotalPriceValue.getText();

        log.info("Total Price Label: " + totalPrice);
        log.info("Total Price Value: " + totalPriceValue);
        return totalPriceValue;
    }


}
