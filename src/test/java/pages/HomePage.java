package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    WebDriver driver;

    //constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    //locators on page

    //Locator to enter the Home Page
    private By homePageBanner=By.xpath("//*[@id=\"meganav-link-5\"]");

    // Locator to enter BedRoom category
    private By clickBedroom=By.xpath("/html/body/section[1]/section[1]/div/div[1]/div[3]/div/div/div[2]/div[1]/div/div/div/div[1]/div[2]/div[3]/a");

    // Locator to enter DiningRoom category

    private By clickDiningRoom=By.xpath("/html/body/section[1]/section[1]/div/div[1]/div[3]/div/div/div[2]/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div[5]/a");

    // Locator to change language
    private By clikcIL=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[9]/button/img");

    // Locator to change language to hebrew
    private By clickHebrew=By.className("header-6pbnht");

    // Locator to submit language to hebrew
    private By clickSubmitLanguage=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");

    // Locator to search product
    private By searchProduct=By.xpath("//*[@id=\"header-big-screen-search-box\"]");

    // Locator to search

    private By clickSearch=By.xpath("//*[@id=\"header-search-form\"]/button");

    //Method

    public void clickHomeBanner(){
        Actions act=new Actions(driver);
      WebElement doubelClickHome= driver.findElement(homePageBanner);
      act.doubleClick(doubelClickHome).perform();
    }
    public void clickBedRoomCategory(){
        driver.findElement(clickBedroom).click();
    }
    public void clickDiningRoomCategory(){
        driver.findElement(clickDiningRoom).click();
    }
    public void clickILButton(){
        driver.findElement(clikcIL).click();
    }
    public void clickHebrewButton(){
        Actions act=new Actions(driver);
        WebElement doubelClickHome= driver.findElement(clickHebrew);
        act.doubleClick(doubelClickHome).perform();

    }
    public void clickSubmitHebrewLanguage(){
        driver.findElement(clickSubmitLanguage).click();
    }
    public void enterProduct(String product){
        driver.findElement(searchProduct).sendKeys(product);
    }
    public void clickSearch(){
        driver.findElement(clickSearch).click();
    }

}
