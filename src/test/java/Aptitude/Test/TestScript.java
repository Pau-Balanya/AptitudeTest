package Aptitude.Test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestScript {
    private List<Double> pricesList = new ArrayList<Double>();

    @Test
    public void TestOrderList() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://highlifeshop.com/speedbird-cafe");

        WebElement sorter = driver.findElement(By.id("sorter"));
        Select dropdown = new Select(sorter);
        dropdown.selectByVisibleText("Price");


        for(int i = 1; i < 11; i++){
            CookieClicker(driver);
            ExtractResultsOnPage(driver);

            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[4]/div[1]/ul/li[7]"));
            nextButton.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        }

        assertTrue(isInOrder(pricesList));

        driver.quit();
    }

    public void CookieClicker(WebDriver driver){

        WebElement cookieAccept = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className("ammodals-overlay")));

        Actions builder = new Actions(driver);
        builder.moveToElement(cookieAccept).click(cookieAccept);
        builder.perform();
    }

    public void ExtractResultsOnPage(WebDriver driver){

        WebElement selectElement = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/ol"));
        List<WebElement> products = selectElement.findElements(By.tagName("li"));

        for(WebElement element : products){

            WebElement container = element.findElement(By.cssSelector("span[data-price-type = 'finalPrice']"));
            String currentString = container.findElement(By.className("price")).getText();
            pricesList.add(Double.parseDouble(currentString.replaceFirst("[^0-9]", "")));
        }

        System.out.println(pricesList.toString());
    }

    public static boolean isInOrder(List<Double> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }

        return true;
    }
}
