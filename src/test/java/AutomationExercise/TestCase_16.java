package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class TestCase_16 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        TestCase_14.enterWebsite(driver);
        //click login button
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='signup-form'] h2")).getText(),
                "New User Signup!");

        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys("ahmeett@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Nuri123.");
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

        String str = driver.findElement(By.xpath("//li[10]//a[1]")).getText();
        Assert.assertEquals(str,"Logged in as ahmet");

        //add products
        Thread.sleep(5000);
        List<WebElement> itemsNameElement = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        WebElement firstElement = itemsNameElement.get(0);
        firstElement.findElement(By.xpath("//div//a[@class='btn btn-default add-to-cart']")).click();

        driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();
        WebElement checkout = driver.findElement(By.cssSelector(".btn.btn-default.check_out"));
        Assert.assertEquals(checkout.getText(),"Proceed To Checkout");

        checkout.click();

        //check address information
        TestCase_14.checkAddress(driver);

        //order message
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Please bring my order carefully");
        driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();

        //payment details
        TestCase_15.enterPaymentDetails(driver);

        //check order
        String orderPlaced = driver.findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Assert.assertEquals(orderPlaced,"ORDER PLACED!");

        //delete account
        TestCase_1.deleteAccount(driver);
        driver.close();
    }
}
