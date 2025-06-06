package com.AB.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage
{
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {                  //note the 'protected' keyword  ----
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); //this driver will inherently wait for 30 seconds
        PageFactory.initElements(driver, this);         //initializes the elements in the page object
    }

    public abstract boolean isPageLoaded() ;

}
