package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //locators

    // Locator for create account
    private By myAccount=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[4]/div[2]/div/a/span");

    // Locator for Email adress
    private By emailLocator=By.name("EmailOrAccountNumber");

    // Locator for password
    private By passwordLocator=By.name("Password");

    // Locator for SignIn button
    private By signInBtnLocator=By.xpath("//*[@id=\"SignInNow\"]");

    //methode

    //Method to create account
    public void clickMyAccount(){
        driver.findElement(myAccount).click();
    }
    //Method to type Email
    public void enterEmailAdress(String emailadress){
        driver.findElement(emailLocator).sendKeys(emailadress);
    }
    //Method to type Password
    public void enterPassword(String password){
        driver.findElement(passwordLocator).sendKeys(password);
    }
    //Method to click SingIn button
    public void clickLoginBtn(){
        driver.findElement(signInBtnLocator).click();
    }


}
