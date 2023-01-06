package AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class TestCase_02 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        String name = "Nuri";
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).
                getAttribute("alt"),"Website for automation practice");
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys("nuriyi@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Nuri123.");
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
        String str = driver.findElement(By.xpath("//li[10]//a[1]")).getText();
        Assert.assertEquals(str,"Logged in as " + name);
        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        String message = driver.findElement(By.xpath("//b[normalize-space()='Account Deleted!']")).getText();
        Assert.assertEquals(message,"ACCOUNT DELETED!");
    }
}
