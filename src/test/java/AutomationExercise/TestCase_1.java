package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class TestCase_1{
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='signup-form'] h2")).getText(),
                "New User Signup!");
        String name = "Nuri";
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("nuriti@gmail.com");
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("Nuri123.");
        driver.findElement(By.cssSelector(" select[id='days'] option[value='5']")).click();
        driver.findElement(By.cssSelector("select[id='months'] option[value='6']")).click();
        driver.findElement(By.cssSelector("option[value='1997']")).click();
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        driver.findElement(By.id("first_name")).sendKeys("Nuri");
        driver.findElement(By.id("last_name")).sendKeys("Ala");
        driver.findElement(By.id("company")).sendKeys("Inar");
        driver.findElement(By.id("address1")).sendKeys("John Street");
        driver.findElement(By.id("address2")).sendKeys("no:5");
        driver.findElement(By.cssSelector("option[value='United States']")).click();
        driver.findElement(By.id("state")).sendKeys("Florida");
        driver.findElement(By.id("city")).sendKeys("Miami");
        driver.findElement(By.id("zipcode")).sendKeys("35555");
        driver.findElement(By.id("mobile_number")).sendKeys("5552221100");
        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        String accountCreated = driver.findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Assert.assertEquals(accountCreated,"ACCOUNT CREATED!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        String str = driver.findElement(By.xpath("//li[10]//a[1]")).getText();
        Assert.assertEquals(str,"Logged in as " + name);
        deleteAccount(driver);
        driver.close();

    }

    private static void deleteAccount(WebDriver driver) {
        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Continue']")).click();
    }
}
