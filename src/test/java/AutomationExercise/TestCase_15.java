package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class TestCase_15 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        enterWebsite(driver);
        //click login button
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='signup-form'] h2")).getText(),
                "New User Signup!");

        TestCase_1.createAccount(driver,"ali","aliali@gmail.com");
        TestCase_1.accountCreationCheck(driver,"ali");

        //find products name get first product and click.
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
        enterPaymentDetails(driver);

        //check order
        String orderPlaced = driver.findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Assert.assertEquals(orderPlaced,"ORDER PLACED!");

        //delete account
        TestCase_1.deleteAccount(driver);
        driver.close();

    }

    public static void enterPaymentDetails(WebDriver driver) {
        driver.findElement(By.name("name_on_card")).sendKeys("card");
        driver.findElement(By.name("card_number")).sendKeys("1111222233334444");
        driver.findElement(By.name("cvc")).sendKeys("311");
        driver.findElement(By.name("expiry_month")).sendKeys("03");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");
        driver.findElement(By.id("submit")).click();
    }

    public static void enterWebsite(WebDriver driver) {
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
    }
}
