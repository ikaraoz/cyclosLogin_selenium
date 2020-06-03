import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyStepDefs {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String arg0) {
        driver.get(arg0);
    }

    @When("the user enters {string} to {string}")
    public void theUserEntersTo(String arg0, String arg1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(arg1))).sendKeys(arg0);
        // Elementing gorunur olmasini beklemek icin wait kullandik.
        // driver.findElement(By.name(arg1)).sendKeys(arg0);
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
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//*[contains(@class, 'notificationText notificationText')]"))).getText());

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'notificationText notificationText')]")).
                getText().contains(arg0));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


}
