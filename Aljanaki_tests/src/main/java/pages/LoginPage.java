package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(css="#CustomerEmail")
    WebElement custEmail;

    @FindBy(css="#CustomerPassword")
    WebElement custPassw;

    @FindBy(css="#CustomerLoginForm button[type='submit']")
    WebElement btnSignIn;

    @FindBy(css="#customer_register_link")
    WebElement btnCreateAccount;

    @FindBy(xpath="//*[@id=\"NewsletterPopup-newsletter-popup\"]/div/div/button")
    WebElement newsletterPopup;

    RegisterPage registerpage;
    AccountPage accountpage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public AccountPage loginUser(String email, String password) throws InterruptedException {

        Thread.sleep(2000); // delay is required to avoid recaptcha
        custEmail.sendKeys(email);
        Thread.sleep(2000); // delay is required to avoid recaptcha
        custPassw.sendKeys(password);
        Thread.sleep(2000); // delay is required to avoid recaptcha
        btnSignIn.click();

        accountpage = new AccountPage(driver);
        return accountpage;

    }

    public RegisterPage createUserAccount() {
        btnCreateAccount.click();
        registerpage = new RegisterPage(driver);
        return registerpage;
    }

    public void closePopUp(){
        newsletterPopup.click();
    }


}
