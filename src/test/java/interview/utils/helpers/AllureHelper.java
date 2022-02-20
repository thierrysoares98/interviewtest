package interview.utils.helpers;

import static io.qameta.allure.Allure.getLifecycle;

import java.util.UUID;

import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

public class AllureHelper {

	public synchronized static void newStep(String message, boolean takeScreenshot) {
			final String uuid = UUID.randomUUID().toString();
			final StepResult stepResult = new StepResult().withName(message);
			
			getLifecycle().startStep(uuid, stepResult);
//			if(takeScreenshot) ScreenshotUtils.takeScreenshot_("");
			getLifecycle().stopStep(uuid);	

	}
	
	public synchronized static void newSkippedStep(String message, boolean takeScreenshot) {
			final String uuid = UUID.randomUUID().toString();
			final StepResult stepResult = new StepResult().withName(message);
			
			getLifecycle().startStep(uuid, stepResult);
			getLifecycle().updateStep(uuid,  s -> s.withStatus(Status.SKIPPED));
//			if(takeScreenshot) ScreenshotUtils.takeScreenshot_("");
			getLifecycle().stopStep(uuid);

	}
	
	public synchronized static void newBrokenStep(String message, boolean takeScreenshot) {
			final String uuid = UUID.randomUUID().toString();
			final StepResult stepResult = new StepResult().withName(message);
			
			getLifecycle().startStep(uuid, stepResult);
			getLifecycle().updateStep(uuid,  s -> s.withStatus(Status.BROKEN));
//			if(takeScreenshot) ScreenshotUtils.takeScreenshot_("");
			getLifecycle().stopStep(uuid);
	}
	
	public synchronized static void newFailStep(String message, boolean takeScreenshot) {

			final String uuid = UUID.randomUUID().toString();
			final StepResult stepResult = new StepResult().withName(message);
			
			getLifecycle().startStep(uuid, stepResult);
			getLifecycle().updateStep(uuid,  s -> s.withStatus(Status.FAILED));
//			if(takeScreenshot) ScreenshotUtils.takeScreenshot_("");
			getLifecycle().stopStep(uuid);
	}

}
