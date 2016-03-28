package Utils;

/**
 * Created by pc on 23.03.2016.
 */
public class Constants {
    public static final String CHROME_DRIVER_ENV_VAR = "webdriver.chrome.driver";
    public static final String EXEC_PATH = "c:/job/";
    public static final String CHROME_DRIVER_EXEC = "chromedriver.exe";
    public static final String GRID_EXEC = "selenium-server-standalone-2.53.0.jar";
    public static final String EXTENT_REPORT_PATH = "target/extent/";
    public static final String EXTENT_REPORT_FILE = "index.html";
    public static final String SCREENSHOT_EXT = ".png";
    public static final int GRID_HUB_PORT = 4444;
    public static final int GRID_NODE1_PORT = 5555;
    public static final int GRID_NODE2_PORT = 5556;
    public static final String GRID_DRIVER_HUB_URL = "http://localhost:" + GRID_HUB_PORT + "/wd/hub";
    public static final String GRID_NODE_HUB_URL = "http://localhost:" + GRID_HUB_PORT + "/grid/register";
    public static final String GRID_HUB_CMD = "java -jar " + EXEC_PATH + GRID_EXEC + " -role hub -port " + GRID_HUB_PORT;
    public static final String GRID_NODE_CMD = "java -jar " + EXEC_PATH + GRID_EXEC
            + " -role node -hub " + GRID_NODE_HUB_URL + " -port %s -browser \"browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20\" -Dwebdriver.chrome.driver=\""
            + EXEC_PATH + CHROME_DRIVER_EXEC + "\" -maxSession 2";
    public static final String KILL_GRID_CMD = "WMIC PROCESS WHERE \"CommandLine like '%" + GRID_EXEC + "%'\" DELETE";

}
