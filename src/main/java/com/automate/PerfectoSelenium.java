package com.automate;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;
import java.util.List;

public class PerfectoSelenium {

    public static void main(String[] args) {
        String securityToken = "<token>";
        String browserName = "Chrome";

        DesiredCapabilities capabilities = new DesiredCapabilities(browserName,  "", Platform.ANY);
        capabilities.setCapability("securityToken", securityToken);
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "86");
        capabilities.setCapability("location", "US East");
        capabilities.setCapability("resolution", "1024x768");

        RemoteWebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("https://testingcloud.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
        } catch(SessionNotCreatedException e) {
            throw new RuntimeException("Driver not created with capabilities: " + capabilities.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            driver.get("https://google.com/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.name("q")).sendKeys("cheese");
            driver.findElement(By.name("btnK")).click();
            List<WebElement> results = driver.findElements(By.cssSelector("#search > div > #rso > .g:not(.g-blk)"));

            Boolean found;
            String resultLink;

            for(WebElement result : results) {
                resultLink = result.findElement(By.cssSelector("div.rc > div > a")).getAttribute("href");
                System.out.println(resultLink);
                found = resultLink.contains("cheese.com");
                if(found) {
                    System.out.println("cheese.com Link Found on First Page");
                    break;
                }
            }

        } finally {
            driver.quit();
        }
    }
}