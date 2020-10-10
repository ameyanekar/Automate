package com.automate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class MyFirstAutomation {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libs/chromedriver" );
        WebDriver driver = new ChromeDriver();
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