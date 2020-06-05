package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    public static WebElement element = null;

    public static void navigates(WebDriver driver, String url) {
        driver.get(url);
    }

    public static WebElement enterUserName(WebDriverWait wait) {
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("principal")));
        return element;
    }
    public static WebElement enterPassword(WebDriverWait wait) {
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        return element;
    }

    public static WebElement clickSignIn(WebDriver driver){
        element= driver.findElement(By.xpath("//*[@class='actionButton']"));
        return element;
    }


}
