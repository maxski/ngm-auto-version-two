package Utils;

import java.io.IOException;

/**
 * Created by pc on 23.03.2016.
 */
public class CMD {

    public static void runCmd(String cmd){
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Exec cmd: " + cmd);
    }

    public static String getTaskKillCmd(String processName){
        return "taskkill /IM " + processName + " /F /T";
    }

    public static void killProcess(String processName){
        String cmd = getTaskKillCmd(processName);
        runCmd(cmd);
    }

    public static void killChromedriver(){
        killProcess(Constants.CHROME_DRIVER_EXEC);
    }
}
