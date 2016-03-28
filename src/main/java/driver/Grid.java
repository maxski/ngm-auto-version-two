package driver;

import Utils.CMD;
import Utils.Constants;
import Utils.Misc;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by pc on 23.03.2016.
 */
public class Grid {
    //java -jar selenium-server-standalone-2.53.0.jar -role node "http://localhost:4445/grid/register" -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20" -Dwebdriver.chrome.driver="C:\job\chromedriver.exe" -maxSession 2
    //java -jar selenium-server-standalone-2.53.0.jar -role node -port 5555 -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20" -Dwebdriver.chrome.driver="C:\job\chromedriver.exe" -maxSession 2
    //java -jar selenium-server-standalone-2.53.0.jar -role node -port 5556 -browser "browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=20" -Dwebdriver.chrome.driver="C:\job\chromedriver.exe" -maxSession 2
    //java -jar selenium-server-standalone-2.53.0.jar -role hub
    public static void startHub(){
        CMD.runCmd(Constants.GRID_HUB_CMD);
        Misc.sleep(5);
    }

    public static void startNodes(){
        startNode(Constants.GRID_NODE1_PORT);
        startNode(Constants.GRID_NODE2_PORT);
    }

    public static void startNode(int port){
        String cmd = String.format(Constants.GRID_NODE_CMD, port);
        CMD.runCmd(cmd);
        Misc.sleep(5);
    }

    public static void stopHubAndNodes(){
        CMD.runCmd(Constants.KILL_GRID_CMD);
    }
}
