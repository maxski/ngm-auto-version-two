package tests;

import Utils.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import driver.DriverUtils;
import driver.FinderImage;
import driver.PatternMatcher;
import org.openqa.selenium.*;
import org.sikuli.core.search.TemplateMatcher;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pc on 23.03.2016.
 */
public class Test5 extends BaseTest {
    private WebDriver driver;
    private ExtentTest test;

    @Test (description = "Verify ukr.net")
    public void test5() throws InterruptedException {
        step1();
        Thread.sleep(3000);  // Let the user actually see something!
        step1_1();
        step2();
    }

    private void step1(){
        getDriver().get("http://www.ukr.net");
    }

    private void step1_1(){
        /*List<TemplateMatcher.Result> matches;
        BufferedImage screenImage = null;
        BufferedImage targetImage = null;
        try {
            //String path = "target/extent/test5.png";

            screenImage = ImageIO.read(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE));
            //screenImage = ImageIO.read(new File(Constants.EXEC_PATH + "source.png"));
            targetImage = ImageIO.read(new File(Constants.EXEC_PATH + "target.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        matches = TemplateMatcher.
                findMatchesByGrayscaleAtOriginalResolution(screenImage, targetImage, 1, 0.8);
        if(!matches.isEmpty()){
            System.out.println(matches.get(0).getScore() + " score");
            System.out.println(matches.get(0).getLocation() + " location");
        }else{
            System.out.println("Nothing found");
        }*/

        for(int i = 0; i < 5; i++){
            PatternMatcher matcher = new PatternMatcher(getDriver(), Constants.EXEC_PATH + "target.png", 0.8);
            System.out.println(matcher.getCenter(0));
            System.out.println(matcher.getSimilarity(0));

            FinderImage finder = new FinderImage(getDriver(), Constants.EXEC_PATH + "target.png", 0.8f);
            System.out.println(finder.getCenter());
            System.out.println(finder.getSimilarity());
        }

    }

    private void step2(){
        WebElement searchBox = getDriver().findElement(By.name("qqq11"));
        searchBox.click();
    }
}
