package tests;

import com.relevantcodes.extentreports.ExtentTest;
import driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by pc on 23.03.2016.
 */
public class Test3 extends BaseTest {
    private WebDriver driver;
    private ExtentTest test;
    @Test (description = "Verify Meta 2")
    public void test3() throws InterruptedException {
        //initDriver();
        step1();
        Thread.sleep(3000);  // Let the user actually see something!
        step2();
        Thread.sleep(3000);  // Let the user actually see something!
    }

    private void step1(){
        getDriver().get("http://www.google.com/xhtml");
    }

    private void step2(){
        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
    }
}
