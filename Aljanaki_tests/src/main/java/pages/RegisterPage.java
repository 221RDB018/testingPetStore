package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;

    @FindBy(id="FirstName")
    WebElement firstname;

    @FindBy(id="LastName")
    WebElement lastName;

    @FindBy(id="Email")
    WebElement email;

    @FindBy(id="CreatePassword")
    WebElement password;

    @FindBy(css="input[value='Create']")
    WebElement btnCreate;

    WebDriverWait wait;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String fn, String ln, String em, String pw) throws InterruptedException {

        //Add thread.sleep() to avoid recaptcha appearance by slowing the test speed
        Thread.sleep(3000); //3 sec
        firstname.sendKeys(fn);
        Thread.sleep(3000); //3 sec
        lastName.sendKeys(ln);
        Thread.sleep(3000); //3 sec
        email.sendKeys(em);
        Thread.sleep(3000); //3 sec
        password.sendKeys(pw);

        Thread.sleep(3000); //3 sec
        btnCreate.click();
    }

    public String getErrorMessageNotUnique() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create_customer\"]/div/ul/li"))).getText();
        return error;


    }


    public String getErrorMessageNoPassword() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create_customer\"]/div/ul/li[1]"))).getText();
        return error;
    }

    public String getErrorMessageNoEmail() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create_customer\"]/div/ul/li[2]"))).getText();
        return error;
    }

}
