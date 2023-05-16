package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    WebDriver driver;

    //constructor
    public PaymentPage(WebDriver driver){
        this.driver=driver;
    }

    //Locators on page
    private By cardNumber=By.xpath("//*[@id=\"cardNumber\"]");
    private By expiryMonth=By.linkText("חודש");
    private By expiryYear=By.linkText("שנה");
    private By securityCode=By.id("securityCode");
    private By submitButton=By.cssSelector("input[id=\"submitButton\"]");
    private By cardErrorMassege=By.cssSelector("input[data-mandatory-message=\"הזן מספר כרטיס תקין\"]");

    ///Methods
    public void creditCardPayment(){
        WebElement check=driver.findElement(By.cssSelector("div[class=\"repeaterTable last\"]"));
        check.click();
        Boolean radioCheck=check.isSelected();
        System.out.println(radioCheck);
    }
    public void enterCardNumber(String cardNumbers){
        driver.findElement(cardNumber).sendKeys(cardNumbers);
    }
    public void enterExpiryMonth(String eexpiryMonth){
        driver.findElement(expiryMonth).sendKeys(eexpiryMonth);
    }
    public void enterExpiryYear(String eexpiryYear){
        driver.findElement(expiryYear).sendKeys(eexpiryYear);
    }
    public void enterSecurityCode(String ssecurityCode){
        driver.findElement(securityCode).sendKeys(ssecurityCode);
    }
    public void clickSubmit(){
        driver.findElement(submitButton).click();
    }
    public String getCardErrorMassege(){
        return driver.findElement(cardErrorMassege).getText();
    }


}
