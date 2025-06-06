package com.AB.pages.flightreservation;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RegistrationPage extends AbstractPage {

    @FindBy(id="firstName")
    private WebElement firstName;

    @FindBy(xpath="//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath="//input[@id='email']")
    private WebElement email;

    @FindBy(xpath="//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='street']")
    private WebElement street;

    @FindBy(xpath="//input[@name='city']")
    private WebElement city;

    @FindBy(xpath="//input[@name='zip']")
    private WebElement zip;

    @FindBy(xpath="//button[@id='register-btn']")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
        return this.firstName.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void enterUserAddress(String street, String city, String zip) {
        this.street.sendKeys(street);
        this.city.sendKeys(city);
        this.zip.sendKeys(zip);
    }

    public void registerUser() {
        this.registerButton.click();
    }

}
