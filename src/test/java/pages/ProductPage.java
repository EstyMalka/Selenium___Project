package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    WebDriver driver;

    //constructor
    public ProductPage(WebDriver driver){
        this.driver=driver;
    }

    //Locators on page
    private By dropDownColour=By.xpath("//*[@id=\"Style712094\"]/section/div[4]/div[1]/label");
    private By dropDownSize=By.linkText("בחירת מידה");
    private By addToBag=By.linkText("הוסף לסל");
    private By clickBag=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div[2]/a/div");
    private By viewOrEditBag=By.linkText("הצג/ערוך סל");
    private By quantityBtn=By.xpath("//*[@id=\"dk_container_Qty_1\"]/a");
    private By checkOut=By.linkText("לקופה");

    //Method

    public void clickDropDownColour(){
        driver.findElement(dropDownColour).click();
    }
    public void chooseColour(){
        WebElement radio=driver.findElement(By.cssSelector("span[style=\"background-image: URL(https://xcdn.next.co.uk/Common/Items/Default/Default/ItemImages/AltItemSwatch/11x11/239415.jpg)\"]"));
        radio.click();
        Boolean radioCheck=radio.isSelected();
        System.out.println(radioCheck);
    }
    public void clickDropDownSize(){
        driver.findElement(dropDownSize).click();
    }

    public void chooseSize(){
        WebElement radio2=driver.findElement(By.linkText("UK 125x160 - 46 ₪"));
        radio2.click();
        Boolean radioCheck=radio2.isSelected();
        System.out.println(radioCheck);
    }
    public void clickAddToBag(){
        driver.findElement(addToBag).click();
    }
    public void clickBagBtn(){
        driver.findElement(clickBag).click();
    }
    public void viewOrEditBagBtn(){
        driver.findElement(viewOrEditBag).click();
    }
    public void clickQuantity(){
        driver.findElement(quantityBtn).click();
    }
    public void addProductToBag(){
        WebElement radio3=driver.findElement(By.linkText("2"));
        radio3.click();
        Boolean radioCheck=radio3.isSelected();
        System.out.println(radioCheck);
    }
    public void clickCheckOut(){
        driver.findElement(checkOut).click();
    }

    public String getSelectedColour(){
        return driver.findElement(By.cssSelector("span[style=\"background-image: URL(https://xcdn.next.co.uk/Common/Items/Default/Default/ItemImages/AltItemSwatch/11x11/239415.jpg)\"]")).getText();
    }
    public String getSelectedSize(){
        return driver.findElement(By.linkText("UK 125x160 - 46 ₪")).getText();
    }

}
