package com.AB.tests;

import com.AB.listener.TestListener;
import com.AB.util.Config;
import com.AB.util.Constants;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

//@Listeners({TestListener.class}) //this will add the TestListener class to the testng.xml file, so that it can be used in all the tests
public class AbstractTest {

    protected WebDriver driver;
    public static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite                                    //this method is called only once before all the tests
    public void setupConfig() throws Exception {
        Config.initialize();                        //this method is needed for Config.get to work
    }

    @BeforeTest
    public void setupDriver(ITestContext context) throws MalformedURLException {

        this.driver = Boolean.parseBoolean(Config.getProperty(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        context.setAttribute(Constants.DRIVER, driver); //this will set the driver in the context for all the tests, for each thread, there will be one context, this will help in taking screenshot of failed tests
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
//        capabilities = (System.getProperty("browser").equalsIgnoreCase("chrome")) ? new ChromeOptions() : new FirefoxOptions();
        capabilities = (Config.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.CHROME)) ? new ChromeOptions() : new FirefoxOptions();

        String urlFormat = Config.getProperty(Constants.GRID_URL_FORMAT);
        String hubHost = Config.getProperty(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);  // will replace the '%s' in ---> ("http://%s:4444/wd/hub", with hubHost);
        log.info("Connecting to Selenium Grid at: " + url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

 /*   @AfterMethod        //It will wait for 5 seconds after each test, uncomment for debugging
    public void sleep()
    {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5)); //sleep for 5 seconds after each test
    }*/

}
