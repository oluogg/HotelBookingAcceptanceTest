package com.EE.utility;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks {

//    private static  Logger LOG = Logger.;
    public static WebDriver driver;

    @Before
    public static WebDriver setDriver() throws IOException {

        String operating_system = Support.getConfigDataFileData("operating_system");

        switch (operating_system){
            case "mac":
                String chrome_for_mac = Support.getConfigDataFileData("chrome_for_mac");
                browserSwitch(chrome_for_mac);
                break;
            case "windows":
                String chrome_for_win = Support.getConfigDataFileData("chrome_for_win");
                browserSwitch("chrome_for_win");
                break;
        }
        return driver;
    }

    /*Selects relevant driver based on operating system type*/
    private static void browserSwitch(String browserBYPlatform) throws IOException {
        String browser = Support.getConfigDataFileData("browser");
        switch (browser){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-popup-blocking");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                System.setProperty("webdriver.chrome.driver", browserBYPlatform);
                driver = new ChromeDriver(capabilities);
                break;

//            case "firefox":
//                driver = new FirefoxDriver();
//                break;

            default:
//                LOG.error("Driver not identified. Please check your config file");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    /*Closes a session of webdrive instance after a test run*/
    @After
    public static void tearDown(Scenario scenario){
//        Support.embedScreenshot(scenario);
        driver.quit();
    }
}
