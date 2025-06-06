package com.AB.pages.vendorportal;

import com.AB.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id="annual-earning")
    private WebElement annualEarning;

    @FindBy(id="profit-margin")
    private WebElement profitMargin;

    @FindBy(id="available-inventory")
    private WebElement availableInventory;

    @FindBy(xpath="//div[@id='dataTable_filter']//input[@type='search']")
    private WebElement searchBox;

    @FindBy(id="dataTable_info")
    private WebElement searchResultsCount;

    @FindBy(xpath="//img[@class='img-profile rounded-circle']")
    private WebElement userProfilePicture;

    @FindBy(xpath="//a[@data-target='#logoutModal']")
    private WebElement logoutLink;

    @FindBy(xpath="//div[@id='logoutModal']//a[text()='Logout']")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {

        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }

    public String getMonthlyEarning() {
        return this.monthlyEarning.getText();
    }

    public String getAnnualEarning() {
        return this.annualEarning.getText();
    }

    public String getProfitMargin() {
        return this.profitMargin.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventory.getText();
    }

    public void searchOrderHistory(String searchText) {
        this.searchBox.clear();
        this.searchBox.sendKeys(searchText);
    }

    public int getSearchResultsCount() {
        String result = this.searchResultsCount.getText();
        System.out.println("Search results count: " + result);
        String [] arr = result.split(" ");
        int value = Integer.parseInt(arr[5]);
        logger.info("Search results count: " + value);
        return value;

    }

    public void logout() {
        this.userProfilePicture.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
    }
}
