package interview.utils.helpers;

public class ThreadHelper {
	
	public static long getThreadId() {
		return  Thread.currentThread().getId();
	}

	public static void wait_(long time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	
}
