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

public class LoginTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {

            {
                "kolluralbert@gmail.com",
                "Albertk02!!",
                true
            },

            {
                "albert@gmail.com",
                "wrongpass12!",
                false
            }
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username,
                          String password,
                          boolean expectedSuccess) {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("https://test.fieldforceconnect.com");

            driver.manage().window().maximize();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            // Email / Mobile
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_1_']")
                    )
            ).sendKeys(username);

            // Password
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_2_']")
                    )
            ).sendKeys(password);

            // Sign In
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='root']/div[2]/span[2]/div/div[2]/form/div[4]/button[1]/span[1]")
                    )
            ).click();

            // Validation
            if (expectedSuccess) {

                wait.until(d ->
                        !d.getCurrentUrl().equals(
                                "https://test.fieldforceconnect.com/"
                        )
                );

                System.out.println("VALID LOGIN TEST PASSED");

            } else {

                boolean errorDisplayed = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
                By.className("go2072408551")
        )
).isDisplayed();

Assert.assertTrue(
        errorDisplayed,
        "Expected invalid login popup was not displayed"
);

System.out.println("INVALID LOGIN TEST PASSED");
            }

        } finally {

            driver.quit();
        }
    }
}