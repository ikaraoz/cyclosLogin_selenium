package Steps;

import com.sun.glass.ui.Pixels;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Pages.LogInPage.*;


public class MyStepDefs {

    WebDriver driver;
    WebDriverWait wait;
    //  JQuerySliderPage sliderPage;

    @Before
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String arg0) {
        navigates(driver, arg0);
        // driver.findElement(By.xpath("//*[@title='Accept Cookies']")).click();

        //driver.manage().deleteAllCookies();
    }

    @When("the user enters {string} to {string}")
    public void theUserEntersTo(String arg0, String arg1) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(arg1))).sendKeys(arg0);
        // Elementing gorunur olmasini beklemek icin wait kullandik.
        // driver.findElement(By.name(arg1)).sendKeys(arg0);
    }

    @When("the user enters username {string}")
    public void theUserEntersUsername(String arg0) {
        enterUserName(wait).sendKeys(arg0);
    }

    @And("the user enters password {string}")
    public void theUserEntersPassword(String arg0) {
        enterPassword(wait).sendKeys(arg0);

    }


    @And("the user clicks sign in button")
    public void theUserClicksSignInButton() {
        clickSignIn(driver).click();
    }

    @And("the user clicks {string}")
    public void theUserClicks(String arg0) {
        driver.findElement(By.xpath(arg0)).click();
    }

    @Then("the user views {string}")
    public void theUserViews(String arg0) {

        String welcomeMessage = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//*[@class='pageSectionHeading']"))).getText();
        Assert.assertEquals(arg0, welcomeMessage);
        //Assert.assertEquals("Message passed", arg0, welcomeMessage);

        // Asagidaki code welcome messagei yazdirmak icin.
//        String welcomeMessage= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='pageSectionHeading']"))).getText();
//        System.out.println("welcomeMessage = " + welcomeMessage);
    }

    @Then("the user views errorMessage {string}")
    public void theUserViewsErrorMessage(String arg0) throws InterruptedException {
        switch (arg0) {
            case "The given name / password are incorrect. Please, try again.":
                String error1 = driver.findElement(By.xpath("//*[@class='notificationText notificationText-singleLine']")).getText();
                Assert.assertEquals(arg0, error1);
                break;
            case "Login name is required":
                String error2 = driver.findElement(By.xpath("//*[text()='Login name is required']")).getText();
                Assert.assertEquals(arg0, error2);
                break;
            case "Password is required":
                String error3 = driver.findElement(By.xpath("//*[text()='Password is required']")).getText();
                Assert.assertEquals(arg0, error3);
                break;
            case "Login name is required\nPassword is required":
                Thread.sleep(3000);

                String error4 = driver.findElement(By.xpath("//*[contains(@class, 'notificationText notificationText')]")).getText();
                System.out.println("error4 = " + error4);
                Assert.assertTrue(error4.contains(arg0));
                break;

            default:
                throw new AssertionError("It does not match with message");
        }
//        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.
//                xpath("//*[contains(@class, 'notificationText notificationText')]"))).getText());
//
//        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.
//                xpath("//*[contains(@class, 'notificationText notificationText')]"))).getText().contains(arg0));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


    @When("the user drags the {string} to {string}")
    public void theUserDragsTheTo(String arg0, String arg1) throws InterruptedException {
        WebElement source = driver.findElement(By.id(arg0));
        WebElement target = driver.findElement(By.id(arg1));
        Actions action = new Actions(driver);
        Thread.sleep(3000);
        action.dragAndDrop(source, target).perform();
    }

    @Then("the user views {string} message")
    public void theUserViewsMessage(String arg0) throws InterruptedException {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("droptarget"))).getText();
        Assert.assertEquals(arg0, message);
    }

    @When("the user slides {string}")
    public void theUserSlides(String arg0) throws InterruptedException {
        WebElement slider = driver.findElement(By.xpath("//*[@type='range']"));
        Thread.sleep(3000);

        Actions actions = new Actions(driver);
        driver.manage().window().maximize();

        // actions.clickAndHold(slider).moveByOffset(40, 0).release().perform();
        Thread.sleep(3000);
//       int pixelsToMove= GetPixelsToMove(slider, 43, 100, 0);
//        actions.clickAndHold(slider).moveByOffset(0,0)
//                //.moveByOffset((-(int)slider.getSize().getWidth()/2), 0)
//                .moveByOffset(pixelsToMove,0).release().perform();

    }

    public static int GetPixelsToMove(WebElement slider, double amount, double sliderMax, double sliderMin) {
        int pixels = 0;
        double tempPixels = slider.getSize().getWidth();
        tempPixels = tempPixels / (sliderMax - sliderMin);
        tempPixels = tempPixels * (amount - sliderMin);
        pixels = (int) tempPixels;
        return pixels;
    }


}
