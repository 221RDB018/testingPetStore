package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.*;

public class AccountPage {

    WebDriver driver;
    WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeNewsletter() {

        try {
            System.out.println("At the loseNewsletter(): start");
            WebElement newsletterModal = driver.findElement(By.cssSelector(".modal__centered-content .newsletter"));
            WebElement btnNotToday = driver.findElement(By.xpath("//button[text()='Not today']"));
            btnNotToday.click();
            System.out.println("At the loseNewsletter(): end");
        }
        catch(NoSuchElementException exception){
            System.out.println("At the loseNewsletter(): "+ NoSuchElementException.DRIVER_INFO);
            return;
        }
    }

    public String getUserNameFromAccountInfo() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("h5"))).getText();
//        System.out.println("User Name at page: " + username);
        return username;


    }

}
