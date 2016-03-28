package driver;

import Utils.Constants;
import Utils.Misc;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pc on 26.03.2016.
 */
public class DriverUtils {

    public static WebDriver getDriver(String hubUrl){
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(hubUrl), getChromeCaps());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static DesiredCapabilities getChromeCaps(){
        return DesiredCapabilities.chrome();
    }


    private static File getScreenshotFile(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    public static BufferedImage getScreenshotImage(WebDriver driver){
        return Misc.getImage(getScreenshotFile(driver).getPath());
    }

    public static String getScreenshotPath(WebDriver driver, String name){
        File scrFile = getScreenshotFile(driver);
        String path = getFilePath(name);
        scrFile.renameTo(new File(path));
        return path;
    }

    private static String getFilePath(String name){
        return Constants.EXTENT_REPORT_PATH + name + Constants.SCREENSHOT_EXT;
    }
}
