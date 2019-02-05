package driver;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.*;
import org.sikuli.script.Image;

import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by pc on 27.03.2016.
 */
public class FinderImage {
    private Match match;

    public FinderImage(WebDriver driver, String pattern, Float similarity){
        timeStamp("Start matching");
        match = getMatch(driver, pattern, similarity);
        timeStamp("Finish matching");
    }

    public double getSimilarity(){
        return match.getScore();
    }

    public Location getCenter(){
        return match.getCenter();
    }

    private Match getMatch(WebDriver driver, String pattern, Float similarity) {
        Match m = null;

        timeStamp("Before screenshot");
        Finder imf = new Finder(new Image(DriverUtils.getScreenshotImage(driver)));
        Instant before = timeStamp("After screenshot");

        try{
            imf.find(new Pattern(pattern).similar(similarity));
            Instant after = timeStamp("Image found");
            System.out.println("Matching duration(ms): " + ChronoUnit.MILLIS.between(before, after));
            m = imf.next();
        }catch(Exception ex){
        }
        return m;
    }

    private Instant timeStamp(String text){
        Instant time = Instant.now();
        System.out.println("SIKULIX-API: "+ text + ": " + time);
        return time;
    }

}
