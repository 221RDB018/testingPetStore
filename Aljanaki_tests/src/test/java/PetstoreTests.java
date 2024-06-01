
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;

public class PetstoreTests {

    WebDriver driver;
    String baseURL = "https://petstore.com";
    HomePage homepage;
    LoginPage loginpage;
    RegisterPage registerpage;
    AccountPage accountpage;
    ChallengePage challengepage;


    @BeforeMethod
    public void setUp() throws Exception {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("disable-infobars");

        options.addArguments("--disable-extensions");

        options.addArguments("--disable-notifications");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(priority = 1)
    public void registerNewCustomer() throws InterruptedException {

            driver.get(baseURL);
            homepage = new HomePage(driver);
            loginpage = homepage.LoginUser();
            registerpage = loginpage.createUserAccount();
            Random random = new Random();
            int num = random.nextInt(100) + 1;
            String email = num + "@mail.com";
            Thread.sleep(12000);
            loginpage.closePopUp();

            registerpage.registerUser("Nina", // your name
                    "Aljanaki", //your surname
                    email, //unique e-mail
                    "1234567890");    //your password
            Thread.sleep(1000);

        String expectedURL = "https://petstore.com/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);

        accountpage = homepage.gotoAccpage();
        String actual = accountpage.getUserNameFromAccountInfo();
        String expected = "NINA ALJANAKI";
        Assert.assertEquals(actual, expected);
    }


    @Test(priority = 2)
    public void registerWithTheSameEmail() throws InterruptedException {

        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        registerpage = loginpage.createUserAccount();
        Thread.sleep(12000);
        loginpage.closePopUp();

        registerpage.registerUser("Nina", // your name
                "Aljanaki", //your surname
                "alny@mail.com", //unique e-mail
                "1234567890");    //your password
        Thread.sleep(1000);
        String actual = registerpage.getErrorMessageNotUnique();
        String expected = "This email address is already associated with an account. If this account is yours, you can reset your password";
        Assert.assertEquals(actual, expected);
    }



    @Test(priority = 3)
    public void registerWithoutData() throws InterruptedException {

        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        registerpage = loginpage.createUserAccount();
        Thread.sleep(12000);
        loginpage.closePopUp();

        registerpage.registerUser(
                "Nina", // your name
                "Aljanaki", //your surname
                "", //unique e-mail
                "");    //your password
        Thread.sleep(5000);
        String actual = registerpage.getErrorMessageNoPassword();
        String expected = "Password can't be blank.";
        Assert.assertEquals(actual, expected);

        actual = registerpage.getErrorMessageNoEmail();
        expected =  "Email can't be blank.";
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 4)
    public void loginRegisteredCustomer() throws InterruptedException {
        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        Thread.sleep(12000);
        loginpage.closePopUp();
        accountpage = loginpage.loginUser("60@mail.com", "1234567890"); // your data
        String actual = accountpage.getUserNameFromAccountInfo();
        String expected = "NINA ALJANAKI";
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 5)
    public void SortByLowesrPriceTest() throws InterruptedException {
        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        Thread.sleep(12000);
        loginpage.closePopUp();
        accountpage = loginpage.loginUser("60@mail.com", "1234567890"); // your data

        DogProductsPage dogpage = new DogProductsPage(driver);
        dogpage.gotoDogSection();
        dogpage.sortByLowestPrice();
        dogpage.chooseProduct();
        String actual = dogpage.getNameOfFirstCheepItem();
        String expected = "POMERANIAN PUPPIES AVAILABLE FOR FREE ADOPTION";
        Assert.assertEquals(actual, expected);
    }


    @Test(priority = 6)
    public void SortByHighestPriceTest() throws InterruptedException {
        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        Thread.sleep(12000);
        loginpage.closePopUp();
        accountpage = loginpage.loginUser("60@mail.com", "1234567890"); // your data

        DogProductsPage dogpage = new DogProductsPage(driver);
        dogpage.gotoDogSection();
        dogpage.sortByHighestPrice();
        dogpage.chooseProduct();
        String actual = dogpage.getNameOfFirstExpensiveItem();
        String expected = "FRENCHBULLDOG";
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 7)
    public void SearchForAnItem() throws InterruptedException {
        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        Thread.sleep(12000);
        loginpage.closePopUp();
        accountpage = loginpage.loginUser("60@mail.com", "1234567890"); // your data

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchForAnItem("KONG® CLASSIC DOG TOYS RED SMALL");
        String actual = searchPage.getProductsName();
        String expected = "KONG® CLASSIC DOG TOYS RED SMALL";
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 8)
    public void AddToCart() throws InterruptedException {
        driver.get(baseURL);
        homepage = new HomePage(driver);
        loginpage = homepage.LoginUser();
        Thread.sleep(12000);
        loginpage.closePopUp();
        accountpage = loginpage.loginUser("60@mail.com", "1234567890"); // your data

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchForAnItem("KONG® CLASSIC DOG TOYS RED SMALL");
        searchPage.addToCart();
        Thread.sleep(3000);

        String actual = searchPage.getAmount();
        String expected = "1";
        Assert.assertEquals(actual, expected);

        actual = searchPage.getSum();
        expected = "$18.56";
        Assert.assertEquals(actual, expected);

        actual = searchPage.getName();
        expected = "Kong® Classic Dog Toys Red Small";
        Assert.assertEquals(actual, expected);
    }

}
