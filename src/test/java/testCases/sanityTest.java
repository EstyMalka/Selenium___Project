package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import jdk.net.SocketFlow;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class sanityTest {

    private static WebDriver driver;

    public static ExtentSparkReporter spark=new ExtentSparkReporter("target/Spark/report.html");
    public static ExtentReports report= new ExtentReports();
    LoginPage login=new LoginPage(driver);
    HomePage homePage=new HomePage(driver);
    ProductPage productPage=new ProductPage(driver);
    PaymentPage paymentPage=new PaymentPage(driver);


    @BeforeClass
    public static void beforeClass() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("Before Class");
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(getDataItem("URL",0));
        spark.config().setReportName("My Report");
        report.attachReporter(spark);
    }

    @Test
    public void A_loginTest() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Login Test");
        ExtentTest loginTest = report.createTest("Login Test");
        loginTest.log(Status.INFO, "This is test login");

        // Click to sign in + verification
        login.clickMyAccount();
        waiting();

        try {
            Assert.assertEquals("Fail", Constants.SING_IN_PAGE_TITLE, driver.getTitle());
            loginTest.log(Status.PASS, "Step 1 passed - Sing in started correct: page title is OK");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            loginTest.log(Status.FAIL, "Step 1 failed - Login test failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }

        //sign in with existing account
        login.enterEmailAdress(getDataItem("EMAIL",0));
        waiting();
        login.enterPassword(getDataItem("PASSWORD",0));
        waiting();
        login.clickLoginBtn();
        waiting();
        driver.navigate().to(getDataItem("URL",0));

        //verification that sign in was successful
        try {
            Assert.assertEquals( Constants.HOME_PAGE_TITLE, driver.getTitle());
            loginTest.log(Status.PASS, "Step 2 passed - Sing in was successful");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            loginTest.log(Status.FAIL, "Step 2 failed - Sing in failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
    }
    @Test
    public void B_homePageCategoryTest() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Home Category Test");
        ExtentTest homePageCategoryTest = report.createTest("Home Category Test");

        //Click on the link in the banner + verification
        homePage.clickHomeBanner();
        waiting();
        try {
            Assert.assertEquals( Constants.HOMEPAGE_CATEGORY_TITLE, driver.getTitle());
            homePageCategoryTest.log(Status.PASS, "Step 1 passed - You have successfully entered the home category via the link in the banner ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            homePageCategoryTest.log(Status.FAIL, "Step 1 failed - You could not enter the home category through the link in the banner", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
        //Click on the link on the left side + verification
        homePage.clickBedRoomCategory();
        waiting();
        try {
            Assert.assertEquals( Constants.BEDROOM_CATEGORY_TITLE, driver.getTitle());
            homePageCategoryTest.log(Status.PASS, "Step 2 passed - You successfully entered the Bed Room page through the link on the left side\"); ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            homePageCategoryTest.log(Status.FAIL, "Step 2 failed - You could not enter the Bed Room page through the link on the left side", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }

        driver.navigate().to(getDataItem("URL",1));
        waiting();
        //click of the categories from the center of page + verification
        homePage.clickDiningRoomCategory();
        waiting();
        try {
            Assert.assertEquals( Constants.DININGROOM_CATEGORY_TITLE, driver.getTitle());
            homePageCategoryTest.log(Status.PASS, "Step 3 passed - You successfully entered the Dining Room page through from the center of the page \"); ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            homePageCategoryTest.log(Status.FAIL, "Step 3 failed - You could not enter the Dining Room page through from the center of the page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
        //return to homepage + verification
        driver.navigate().back();
        try {
            Assert.assertEquals( Constants.HOMEPAGE_CATEGORY_TITLE, driver.getTitle());
            homePageCategoryTest.log(Status.PASS, "Step 4 passed - You successfully returned to the Home page \"); ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            homePageCategoryTest.log(Status.FAIL, "Step 4 failed - There was a problem returning to the Home page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }

    }
    @Test
    public void C_changeOfLanguage() throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Change Of Language Test");
        ExtentTest changeOfLanguageTest = report.createTest("Change Of Language Test");

        //change language of website to hebrew
        homePage.clickILButton();
        waiting();
        homePage.clickHebrewButton();
        waiting();
        homePage.clickSubmitHebrewLanguage();
        waiting();
        //verification
        try {
            Assert.assertEquals(Constants.HOME_PAGE_TITLE, driver.getTitle());
            changeOfLanguageTest.log(Status.PASS,"The website has successfully changed to hebrew");
        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            changeOfLanguageTest.log(Status.FAIL, "change Language To Hebrew Test has failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
    }
    @Test
    public void D_productSearch() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("product Search Test");
        ExtentTest productSearch = report.createTest("product Search Test");

        //search for blanket in search box + verification
        homePage.enterProduct(getDataItem("PRODUCT",0));
        waiting();
        homePage.clickSearch();
        waiting();
        try {
            Assert.assertEquals( Constants.BLANKET_PAGE_TITLE, driver.getTitle());
            productSearch.log(Status.PASS, "You have successfully reached the blanket page");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            productSearch.log(Status.FAIL, " There was a problem reached the blanket page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
    }
    @Test
    public void E_productChoose() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Product Choose Test");
        ExtentTest productChoose = report.createTest("Product Choose Test");
        driver.get(getDataItem("URL",2));

        //choose colour + verification
        productPage.clickDropDownColour();
        waiting();
        productPage.chooseColour();
        waiting();
        String expectedColour="אפור כסף";
        try {
            Assert.assertEquals(expectedColour,productPage.getSelectedColour());
            productChoose.log(Status.PASS, "Step 1 passed -The colour אפור כסף has been selected); ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            productChoose.log(Status.FAIL, "Step 1 failed - There was a problem selecting the colour אפור כסף", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }
        //choose size + verification
        productPage.clickDropDownSize();
        waiting();
        productPage.chooseSize();
        waiting();
        String expectedSize="UK 125x160 - 46 ₪";
        try {
            Assert.assertEquals(expectedSize,productPage.getSelectedSize());
            productChoose.log(Status.PASS, "Step 2 passed - The size 125*160 has been selected \"); ");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            productChoose.log(Status.FAIL, "Step 2 failed -There was a problem selecting the size 125*160", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }

    }
    @Test
    public void F_addProductToBag() throws IOException {
        System.out.println("Add Product To Bag Test");
        ExtentTest addProductToBag = report.createTest("Add Product To Bag Test");

        // add to bag + check your bag and go to checkout
        productPage.clickAddToBag();
        waiting();
        productPage.clickBagBtn();
        waiting();
        productPage.clickBagBtn();
        waiting();
        productPage.viewOrEditBagBtn();
        waiting();
        productPage.clickQuantity();
        waiting();
        productPage.addProductToBag();
        waiting();
        productPage.clickCheckOut();
        waiting();

        //verification
        try {
            Assert.assertEquals(Constants.PAYMENT_PAGE_TITLE, driver.getTitle());
            addProductToBag.log(Status.PASS," You successfully reached the Payment Page");
        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            addProductToBag.log(Status.FAIL, "There was a problem reaching the Payment page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());

        }

    }
    @Ignore
    @Test
    public void G_paymentTest() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Payment Test");
        ExtentTest paymentTest = report.createTest("Product Choose Test");
        //choose to pay by credit card
        paymentPage.creditCardPayment();
        waiting();
        //enter payment details and pay
        paymentPage.enterCardNumber(getDataItem("CardNumber",0));
        paymentPage.enterExpiryMonth(getDataItem("ExpiryMonth",0));
        waiting();
        paymentPage.enterExpiryYear(getDataItem("ExpiryYear",0));
        paymentPage.enterSecurityCode(getDataItem("SecurityCode",0));
        paymentPage.clickSubmit();

        //verification error message displayed
        String expectedCardErrorMessage = "הזן מספר כרטיס תקין";
        try{
            Assert.assertEquals(expectedCardErrorMessage,paymentPage.getCardErrorMassege());
            paymentTest.log(Status.PASS," Card error message displayed");

        } catch (AssertionError e) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            paymentTest.log(Status.FAIL, "Card error message is not displayed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "ScreenShot" + currentTime)).build());
        }

    }

    @AfterClass
    public static void AfterClass(){
        waiting();
        driver.quit();
        report.flush();
        System.out.println("After Class");
    }

    //functions
    public static void waiting() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getDataItem (String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException {
        File configXmlFile = new File(Constants.CONFIG_FILE_LOCATION);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(index).getTextContent();
    }
    // A function to create a screenshot:
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}

