package com.AB.cucumber.options;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@CucumberOptions(
        /*features = "src/test/resources/features",
        glue = "com.AB.stepdefs",*/
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/jsonReports/cucumber-report.json"},
        monochrome = true,
        dryRun = false
//        tags = "@DeletePlace"
)
public class TestRunner extends AbstractTestNGCucumberTests
{
}
