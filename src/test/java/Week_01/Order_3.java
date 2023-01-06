package Week_01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Order_3 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Payment information-card test.
         */

        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ASUS\\Desktop\\chromedriver.exe");

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete12%2fWebOrders%2fDefault.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_password']")).sendKeys("test");
        driver.findElement(By.cssSelector("input.button")).click();
        driver.findElement(By.xpath("//tbody/tr[2]/td[13]/input[1]")).click();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//a[@id='ctl00_MainContent_fmwOrder_UpdateButton']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tbody/tr[2]/td[10]")).getText(),
                        "Visa");
        Thread.sleep(1500);
        driver.close();
    }
}
