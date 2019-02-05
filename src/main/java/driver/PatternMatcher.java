package driver;

import Utils.Constants;
import Utils.Misc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.core.search.TemplateMatcher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 26.03.2016.
 */
public class PatternMatcher {
    private List<TemplateMatcher.Result> matches;

    public PatternMatcher(WebDriver driver, String pattern, double similarity){
        System.load(new File(System.getProperty("user.dir") + "/libs/opencv_java2410.dll").getPath());
        timeStamp("Start matching");
        matches = setMatches(driver, pattern, similarity);
        timeStamp("Finish matching");
    }

    public int getSize(){
        return matches.size();
    }

    public double getSimilarity(int index){
        return matches.get(index).getScore();
    }

    public Point getCenter(int index){
        int x = matches.get(index).getX();
        int y = matches.get(index).getY();
        int width = matches.get(index).getWidth();
        int height = matches.get(index).getHeight();
        return new Point(getAvg(width, x), getAvg(height, y));
    }

    private int getAvg(int big, int small){
        return small + (big - small)/2;
    }


    private List<TemplateMatcher.Result> setMatches(WebDriver driver, String pattern, double similarity) {
        timeStamp("Before screenshot");
        BufferedImage screenshotImg = DriverUtils.getScreenshotImage(driver);
        timeStamp("After screenshot");
        BufferedImage patternImg = Misc.getImage(pattern);
        Instant before = timeStamp("Get pattern");
        List<TemplateMatcher.Result> results = TemplateMatcher.
                findMatchesByGrayscaleAtOriginalResolution(screenshotImg, patternImg, 1, similarity);
        System.out.println("Matching duration(ms): " + ChronoUnit.MILLIS.between(before, Instant.now()));
        return results;
    }

    private Instant timeStamp(String text){
        Instant time = Instant.now();
        System.out.println("SIKULI-CORE: "+ text + ": " + time);
        return time;
    }


}
