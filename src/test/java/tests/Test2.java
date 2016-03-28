package tests;

import com.relevantcodes.extentreports.ExtentTest;
import driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by pc on 22.03.2016.
 */
public class Test2 extends BaseTest {
    private WebDriver driver;
    private ExtentTest test;

    @Test(description = "Verify Meta")
    public void test2() throws InterruptedException {
        step1();
        Thread.sleep(3000);  // Let the user actually see something!
        step2();
    }

    private void step1(){
        getDriver().get("http://www.meta.ua");
    }

    private void step2(){
        WebElement searchBox = getDriver().findElement(By.name("qqq11"));
        searchBox.click();
    }
}
