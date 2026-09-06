package com.albert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewCustomerTest {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return new Object[][] {
            {"Test Customer 1"},
            {"Test Customer 2"},
            {"Test Customer 3"}
        };
    }

    @Test(dataProvider = "customerData")
    public void newCustomerTest(String customerName) {

        WebDriver driver = new ChromeDriver();

        try {

            // ------------------------------------------------
            // 1. LOGIN
            // ------------------------------------------------

            driver.get("https://test.fieldforceconnect.com");
            driver.manage().window().maximize();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            // Enter email
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_1_']")
                    )
            ).sendKeys("kolluralbert@gmail.com");

            // Enter password
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_2_']")
                    )
            ).sendKeys("Albertk02!!");

            // Click Sign In
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='root']/div[2]/span[2]/div/div[2]/form/div[4]/button[1]/span[1]")
                    )
            ).click();


            // ------------------------------------------------
            // 2. MY CUSTOMERS
            // ------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/a")
                    )
            ).click();


            // ------------------------------------------------
            // 3. SELECT MY CUSTOMER
            // ------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/a/span")
                    )
            ).click();


            // ------------------------------------------------
            // 4. CLICK MANAGE
            // ------------------------------------------------
            wait.until(
    ExpectedConditions.invisibilityOfElementLocated(
        By.className("go2072408551")
    )
);
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/button")
                    )
            ).click();


            // ------------------------------------------------
            // 5. CLICK NEW CUSTOMER
            // ------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("/html/body/div[2]/div[3]/ul/li[1]/span[1]/span[2]")
                    )
            ).click();


            // ------------------------------------------------
            // 6. ENTER LEAD / CUSTOMER NAME
            // ------------------------------------------------

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("/html/body/div[2]/div[3]/div/div[1]/form/div[1]/div[1]/div/div/div/input")
                    )
            ).sendKeys(customerName);


            // ------------------------------------------------
            // 7. CLICK SAVE
            // ------------------------------------------------

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("/html/body/div[2]/div[3]/div/div[2]/button[2]/span[1]")
                    )
            ).click();


            // ------------------------------------------------
            // 8. VALIDATE CUSTOMER WAS ADDED
            // ------------------------------------------------

            String customerXpath =
                    "//table/tbody/tr/td[3]//span[normalize-space()='" 
                    + customerName + "']";

            String actualCustomerName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(customerXpath)
                    )
            ).getText();

            Assert.assertEquals(
                    actualCustomerName,
                    customerName,
                    "Customer was not added correctly."
            );

            System.out.println(
                    "NEW CUSTOMER TEST PASSED: " + customerName
            );

        } finally {

            // Close browser
            driver.quit();
        }
    }
}