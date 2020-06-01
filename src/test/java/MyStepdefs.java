import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyStepdefs {

    WebDriver driver;
    WebDriverWait wait;
    @Before
    public void driverSetUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
    }

    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String arg0) {
        driver.get(arg0);
    }

    @When("the user enters {string} to {string}")
    public void theUserEntersTo(String arg0, String arg1) {
    }

    @And("the user clicks {string}")
    public void theUserClicks(String arg0) {
    }

    @Then("the user views {string}")
    public void theUserViews(String arg0) {
    }
}
