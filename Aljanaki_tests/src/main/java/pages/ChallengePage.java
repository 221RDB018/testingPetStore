package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ChallengePage {

    WebDriver driver;

    public ChallengePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeReCAPTCHA() throws InterruptedException {

        System.out.println("closeReCAPTCHA: " + driver.getCurrentUrl());

        if (driver.getCurrentUrl().contains("https://petstore.com/challenge")) {

            //WebElement chkNotRobot = driver.findElement(By.cssSelector("#recaptcha-anchor"));
            //chkNotRobot.click();
            Thread.sleep(3000); //3 sec

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.recaptcha.net/recaptcha')]")));

            Thread.sleep(6000); //6 sec
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();


            Thread.sleep(6000); //6 sec
            WebElement btnSubmit = driver.findElement(By.xpath("//input[@value='Submit']"));
            btnSubmit.click();

            //accountpage = new AccountPage(driver);

        }



    }

}
