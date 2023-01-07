package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class TestCase_12 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        enterWebsite(driver);
        driver.findElement(By.xpath("(//ul//li//a)[2]")).click();

        //find products name get first product and click.
        Thread.sleep(5000);
        List<WebElement> itemsNameElement = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        WebElement firstElement = itemsNameElement.get(0);
        firstElement.findElement(By.xpath("//div//a[@class='btn btn-default add-to-cart']")).click();

        //continue shopping
        driver.findElement(By.xpath("//button[normalize-space()='Continue Shopping']")).click();
        itemsNameElement.get(1).findElement(By.xpath("//div//a[@class='btn btn-default add-to-cart']")).click();

        //view cart
        driver.findElement(By.xpath("//div[@class='modal-body']//u")).click();

        //confirm in the cart product
        String productName = driver.findElement(By.xpath("//div[@class='table-responsive cart_info']//h4")).getText();
        Assert.assertEquals(productName,"Blue Top");
        String quantity = driver.findElement(By.cssSelector(".disabled")).getText();
        Assert.assertEquals(quantity,"2");
        String total = driver.findElement(By.cssSelector(".cart_total_price")).getText();
        Assert.assertEquals(total,"Rs. 1000");
        Thread.sleep(5000);
        driver.close();

    }

    public static void enterWebsite(WebDriver driver) {
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
    }
}
