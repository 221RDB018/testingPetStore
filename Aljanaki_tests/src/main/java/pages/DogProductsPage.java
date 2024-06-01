package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DogProductsPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"shopify-section-header\"]/div[3]/div[2]/div/div/header/div[1]/div/div[2]")
    WebElement homepage;

    @FindBy(xpath = "//*[@id=\"shopify-section-header\"]/div[3]/div[2]/div/div/header/div[1]/div/div[1]/div/button")
    WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id=\"Label-collections-dog1\"]")
    WebElement dogSection;

    @FindBy(xpath = "//*[@id=\"SortBy\"]")
    WebElement sortMenu;

    @FindBy(xpath = "//*[@id=\"SortBy\"]/option[6]")
    WebElement sortByLowest;

    @FindBy(xpath = "//*[@id=\"SortBy\"]/option[7]")
    WebElement sortByHighest;

    @FindBy(xpath = "//*[@id=\"CollectionSection\"]/div[2]/div[1]/div/a/div[2]/div[1]")
    WebElement firstItem;

    @FindBy(xpath = "//*[@id=\"ProductSection-6561316274251\"]/div/div/div/div[2]/div/h1")
    WebElement titleOfCheepProduct;

    @FindBy(xpath = "//*[@id=\"ProductSection-6556977463371\"]/div/div/div/div[2]/div/h1")
    WebElement titleOfExpensiveProduct;


    public DogProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void gotoDogSection(){
        homepage.click();
        hamburgerMenu.click();
        dogSection.click();
    }

    public void sortByLowestPrice(){
        sortMenu.click();
        sortByLowest.click();
    }

    public void sortByHighestPrice(){
        sortMenu.click();
        sortByHighest.click();
    }

    public void chooseProduct(){
        firstItem.click();
    }

    public String getNameOfFirstCheepItem(){
       return titleOfCheepProduct.getText();
    }

    public String getNameOfFirstExpensiveItem(){
        return titleOfExpensiveProduct.getText();
    }

}
