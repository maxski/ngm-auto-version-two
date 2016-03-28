package reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

/**
 * Created by pc on 22.03.2016.
 */
public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter(String filePath) {
    //public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true, NetworkMode.ONLINE);
            extent.addSystemInfo("Host Name", "Max");
            extent.addSystemInfo("Env", "Mobqa2");
        }
        return extent;
    }
}
