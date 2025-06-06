package com.AB.tests.vendorportal;

import com.AB.pages.vendorportal.DashboardPage;
import com.AB.pages.vendorportal.LoginPage;
import com.AB.tests.AbstractTest;
import com.AB.tests.vendorportal.model.VendorPortalTestData;
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

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void pageSetup(String testDataPath) throws IOException {
        System.out.println("TestData Path received: " + testDataPath);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class); //this is where the JSON file is read and converted to a Java object of the class you request
        System.out.println("TestData Path : " + testData);
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest()
    {
        loginPage.goTo(Config.getProperty(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isPageLoaded());
        loginPage.login(testData.username(), testData.password());              //note how we are not using .getUsername() or .getPassword() here but directly .username() or .password()
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest()
    {
        Assert.assertTrue(dashboardPage.isPageLoaded());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());

    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest()
    {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isPageLoaded());
    }
}
