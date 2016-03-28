package tests;

import Utils.CMD;
import Utils.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.DriverUtils;
import driver.Grid;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporter.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by pc on 22.03.2016.
 */
public abstract class BaseTest {
    private WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected final String filePath = Constants.EXTENT_REPORT_PATH + Constants.EXTENT_REPORT_FILE;

    //protected WebDriver getDriver(){
    protected WebDriver getDriver(){
        return this.driver;
    }
    //protected void setDriver(WebDriver driver){
    protected void setDriver(String hubUrl){
        this.driver = DriverUtils.getDriver(hubUrl);
    }

    protected ExtentReports getReport(){
        if (extent == null){
            initReport();
        }
        return extent;
    }

    protected ExtentTest getTestReport(){
        return this.test;
    }

    protected void setTestReport(String methodName){
        this.test = getReport().startTest(methodName);
    }

    /*protected void initDriver(){
        if(getDriver() == null){
            System.setProperty("webdriver.chrome.driver", "c:/job/chromedriver.exe");
    setDriver(new ChromeDriver());
}
}*/

    protected void initDriver(){
        if(getDriver() == null){
            /*System.setProperty("webdriver.chrome.driver", Constants.EXEC_PATH + Constants.CHROME_DRIVER_EXEC);
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            try {
                WebDriver chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                setDriver(chromeDriver);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            setDriver(Constants.GRID_DRIVER_HUB_URL);
        }
    }

    protected void quitDriver(){
        getDriver().quit();
    }

    protected void initReport(){
        extent = ExtentManager.getReporter(filePath);
    }

    @BeforeSuite
    public void beforeSuite() {
        Grid.startHub();
        Grid.startNodes();
        initReport();
        setChromeDriverEnvVar();
        //test = extent.startTest(getMethodName(result));
    }

    @BeforeClass
    public void beforeClass(){
        initDriver();
    }

    @AfterClass
    public void afterClass(){
        quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        getReport().close();
        Grid.stopHubAndNodes();
        CMD.killChromedriver();
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        setTestReport(method.getName());
        getTestReport().log(LogStatus.INFO, "Test started: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        System.out.println(getMethodName(result));
        getTestReport().log(LogStatus.INFO, "Test finished: " + getMethodName(result));
        if(result.isSuccess()){
            getTestReport().log(LogStatus.PASS, "Test passed");
        }else{
            String path = getScreenshot(getMethodName(result));
            getTestReport().log(LogStatus.FAIL, result.getThrowable());
            getTestReport().log(LogStatus.INFO, "Image", "Image example: " + test.addScreenCapture(path));
        }
        getReport().endTest(getTestReport());
        getReport().flush();
    }

    public String getScreenshot(String methodName){
        String path = "target/extent/" + methodName + ".png";
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        System.out.println(path);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return methodName + ".png";
    }

    private String getMethodName(ITestResult result){
        return result.getMethod().getMethodName();
    }

    private void setChromeDriverEnvVar(){
        System.setProperty(Constants.CHROME_DRIVER_ENV_VAR, Constants.EXEC_PATH + Constants.CHROME_DRIVER_EXEC);
    }
}
