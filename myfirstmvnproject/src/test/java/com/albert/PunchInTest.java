package com.albert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PunchInTest {

    @Test
    public void punchInTest() {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("https://test.fieldforceconnect.com");

            driver.manage().window().maximize();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            // Login
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_1_']")
                    )
            ).sendKeys("kolluralbert@gmail.com");

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='_r_2_']")
                    )
            ).sendKeys("Albertk02!!");

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='root']/div[2]/span[2]/div/div[2]/form/div[4]/button[1]/span[1]")
                    )
            ).click();

            // Click Punch In
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='root']/div[2]/div/div[2]/div[2]/div/div[1]/div/div[1]")
                    )
            ).click();

            // Verify Toast Message
            String actualMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("go2072408551")
                    )
            ).getText();

          Assert.assertTrue(
        actualMessage.contains("No data available for this section"),
        "Punch In toast message is incorrect. Actual message: " + actualMessage
);
            System.out.println("PUNCH IN TEST PASSED");

        } finally {

            driver.quit();
        }
    }
}