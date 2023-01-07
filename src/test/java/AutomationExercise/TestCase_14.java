package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class TestCase_14 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        enterWebsite(driver);
        driver.findElement(By.xpath("(//ul//li//a)[2]")).click();

        //find products name get first product and click.
        addProducts(driver);

        driver.findElement(By.xpath("//div[@class='modal-body']//u")).click();
        //proceed to checkout
        driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();
        driver.findElement(By.xpath("//u[normalize-space()='Register / Login']")).click();

        String userName = "ahmet";
        //create account
        TestCase_1.createAccount(driver,userName,"ahmeett@gmail.com");
        TestCase_1.accountCreationCheck(driver,userName);
        driver.findElement(By.xpath("//body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]")).click();
        driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();

        //check address info
        checkAddress(driver);

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

    private static void enterPaymentDetails(WebDriver driver) {
        driver.findElement(By.name("name_on_card")).sendKeys("card");
        driver.findElement(By.name("card_number")).sendKeys("1111222233334444");
        driver.findElement(By.name("cvc")).sendKeys("311");
        driver.findElement(By.name("expiry_month")).sendKeys("03");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");
        driver.findElement(By.id("submit")).click();
    }

    public static void checkAddress(WebDriver driver) {
        List<WebElement> addressInformation = driver.findElements(By.cssSelector("ul[id='address_delivery'] li"));
        Assert.assertEquals("Inar",addressInformation.get(2).getText());
        Assert.assertEquals("5552221100",addressInformation.get(7).getText());
    }

    public static void addProducts(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> itemsNameElement = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        WebElement firstElement = itemsNameElement.get(0);
        firstElement.findElement(By.xpath("//div//a[@class='btn btn-default add-to-cart']")).click();
    }


    public static void enterWebsite(WebDriver driver) {
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
    }
}
