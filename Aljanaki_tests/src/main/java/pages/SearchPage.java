package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"shopify-section-header\"]/div[3]/div[2]/div/div/header/div[1]/div/div[2]")
    WebElement homepage;

    @FindBy(xpath = "//*[@id=\"shopify-section-header\"]/div[3]/div[2]/div/div/header/div[1]/div/div[3]/div/div/a[2]")
    WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"HeaderSearchForm\"]/input[2]")
    WebElement searchField;

    @FindBy(xpath = "//*[@id=\"HeaderSearchForm\"]/button")
    WebElement searchFieldButton;

    @FindBy(xpath = "//*[@id=\"MainContent\"]/div/div/div/div/div[2]/div[1]/div/a/div[2]")
    WebElement product;

    @FindBy(xpath = "//*[@id=\"ProductSection-4660870381643\"]/div/div/div/div[2]/div/h1")
    WebElement productsName;

    @FindBy(xpath = "//*[@id=\"AddToCart-4660870381643\"]")
    WebElement addButton;

    @FindBy(xpath = "//*[@id=\"updates_32809612476491:14404adb313f2805357b9a0dad2439a2\"]")
    WebElement amount;
    @FindBy(xpath = "//*[@id=\"CartContainer\"]/div[2]/div/div[2]/p")
    WebElement sum;

    @FindBy(xpath = "//*[@id=\"CartContainer\"]/div[1]/div/div/div/div[2]/div[1]/a")
    WebElement name;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForAnItem(String item){
        homepage.click();
        searchButton.click();
        searchField.sendKeys(item);
        searchFieldButton.click();
    }

    public String getProductsName(){
        product.click();
        return productsName.getText();
    }

    public void addToCart(){
        product.click();
        addButton.click();
    }

    public String getAmount(){
        return amount.getAttribute("value");
    }

    public String getSum(){
        return sum.getText();
    }

    public String getName(){
        return name.getText();
    }

}
