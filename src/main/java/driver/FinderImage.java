package driver;

import Utils.*;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.*;
import org.sikuli.script.Image;
import org.sikuli.libswin.Sikulix;
import org.sikuli.libswin.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;

/**
 * Created by pc on 27.03.2016.
 */
public class FinderImage {
    private Match match;

    public FinderImage(WebDriver driver, String pattern, Float similarity){
        System.load(new File(System.getProperty("user.dir") + "/libs/opencv_java2410.dll").getPath());
        printTimeStamp("Start matching");
        match = getMatch(driver, pattern, similarity);
        printTimeStamp("Finish matching");
    }

    public double getSimilarity(){
        return match.getScore();
    }

    public Location getCenter(){
        return match.getCenter();
    }

    private Match getMatch(WebDriver driver, String pattern, Float similarity) {
        printTimeStamp("Before screenshot");
        Match m = null;
        ImageFinder imf = new ImageFinder(new Image(DriverUtils.getScreenshotImage(driver)));
        printTimeStamp("After screenshot");
        try{
            imf.find(new Pattern(pattern).similar(similarity));
            printTimeStamp("Image found");
            m = imf.next();
        }catch(Exception ex){
        }
        return m;
    }

    private void printTimeStamp(String text){
        java.util.Date date= new java.util.Date();
        System.out.println("SIKULIX-API: "+ text + ": " + new Timestamp(date.getTime()));
    }

}
