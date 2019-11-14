package com.EE;

//import cucumber.api.CucumberOptions;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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
