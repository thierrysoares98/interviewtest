package interview.utils.helpers;

import java.io.IOException;

public class ProcessKillerHelper {
	
	public static void killChromeDriverProcess() throws IOException {
		Runtime.getRuntime().exec("killall -9 chromedriver");
	}
	
	public static void killFirefoxDriverProcess() throws IOException {
		Runtime.getRuntime().exec("killall -9 firefoxdriver");
	}

}
