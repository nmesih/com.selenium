package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class TestCase_13 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        enterWebsite(driver);
        //view card
        driver.findElement(By.cssSelector("a[href='/product_details/1']")).click();

        //Verify product detail is opened
        String productName = driver.findElement(By.cssSelector("div[class='product-information'] h2")).getText();
        Assert.assertEquals(productName,"Blue Top");

        WebElement quantity = driver.findElement(By.cssSelector("#quantity"));
        quantity.clear();
        quantity.sendKeys("4");

        //add to cart
        driver.findElement(By.cssSelector("button[type='button']")).click();
        driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();

        //Verifying the number of products in the cart
        String quantityNumber = driver.findElement(By.cssSelector(".disabled")).getText();
        Assert.assertEquals(quantityNumber,"4");
        driver.close();
    }

    public static void enterWebsite(WebDriver driver) {
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
    }
}
