package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class HomePage {

    WebDriver driver;
    LoginPage loginpage;

    AccountPage accpage;

    @FindBy(css=".site-nav__link.site-nav__link--icon.small--hide")
    WebElement account_link;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage LoginUser() {

        //driver.findElement(By.cssSelector(".site-nav__link.site-nav__link--icon.small--hide")).click();
        account_link.click();
        loginpage = new LoginPage(driver);
        return loginpage;

    }

    public AccountPage gotoAccpage(){
        account_link.click();
        accpage = new AccountPage(driver);
        return accpage;
    }


}
