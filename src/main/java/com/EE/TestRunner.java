package com.EE;

//import cucumber.api.CucumberOptions;
//import io.cucumber.junit.CucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/EE/feature",
        tags = {"@web, @api" },
        plugin = { "pretty", "html:target/html", "json:target/cucumber.json" }
        )

// use this class to trigger all the tests
public class TestRunner {
}
