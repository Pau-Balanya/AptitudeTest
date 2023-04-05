package Login.Test;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Debug;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginScript {

    final String myUrl = "https://www.stealmylogin.com/demo.html";
    final String expectedUrl = "https://example.com/";

    private String username = "example";
    private String password = "example";

    @Test
    public void TestLogin(){

        WebDriver driver = new EdgeDriver();
        driver.get(myUrl);

        //Input the data
        WebElement usernameBox = driver.findElement(By.name("username"));
        WebElement passwordBox = driver.findElement(By.name("password"));

        usernameBox.clear();
        passwordBox.clear();

        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);

        //Press buttton
        WebElement loginButton = driver.findElement(By.xpath("/html/body/form/input[3]"));
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        System.out.println("CURRENT URL " + currentUrl);
        assertEquals(expectedUrl, currentUrl);

        driver.quit();

    }
}
