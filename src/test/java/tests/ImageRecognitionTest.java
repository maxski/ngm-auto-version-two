package tests;

import Utils.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import driver.FinderImage;
import driver.PatternMatcher;
import org.openqa.selenium.*;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

/**
 * Created by pc on 23.03.2016.
 */
public class ImageRecognitionTest extends BaseTest {

    @Test (description = "Image Recognition via SikuliX Api", priority = 1)
    public void sikulixApi() throws InterruptedException {

        getDriver().get("http://www.ukr.net");
        Thread.sleep(3000);

        for(int i = 0; i < 5; i++){
            FinderImage finder = new FinderImage(getDriver(), Constants.EXEC_PATH + "target.png", 0.8f);
            System.out.println("Center: " + finder.getCenter());
            System.out.println("Similarity: " + finder.getSimilarity());
        }
    }

    @Test (description = "Image Recognition via Sikuli Core", priority = 2)
    public void sikuliCore() throws InterruptedException {

        getDriver().get("http://www.ukr.net");
        Thread.sleep(3000);

        for(int i = 0; i < 5; i++){
            PatternMatcher matcher = new PatternMatcher(getDriver(), Constants.EXEC_PATH + "target.png", 0.8);
            System.out.println("Center: " + matcher.getCenter(0));
            System.out.println("Similarity: " + matcher.getSimilarity(0));
        }
    }

}
