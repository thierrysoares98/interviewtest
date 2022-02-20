package interview.utils.helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import interview.utils.utilities.ScreenshotUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LoggerHelper {

	private Logger logger;
	private static int counter = 0;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public LoggerHelper(Class clazz) {
		logger = Logger.getLogger(clazz);
	}

	public void info(String message) {
		PDFHelper.addText(message);
		logger.info(message);
	}


	public void info(String message, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		//AllureHelper.newStep(message, takeScreenshot);
		logger.info(message);
	}

	public void printScreen(String message, boolean takeScreenshot, String stepName)  {
		byte[] screenshootBytes = ScreenshotUtils.printScreen();
		InputStream screenshootStream = new ByteArrayInputStream(screenshootBytes);
		Allure.addAttachment(stepName, screenshootStream);
		logger.info(message);
	}

	public void info(String message, Throwable t) {
		PDFHelper.addText(message);
		info(message, t, false);
	}

	public void info(String message, Throwable t, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		//AllureHelper.newStep(message, takeScreenshot);
		logger.info(message, t);
	}

	public void debug(String message) {
		debug(message, false);
	}

	public void debug(String message, boolean takeScreenshot) {
		if (takeScreenshot) {
			PDFHelper.addTextScreenshot_(message, takeScreenshot);
//			ScreenshotUtils.takeScreenshot_(message);
		}
		logger.debug(message);
	}

	public void debug(String message, Throwable t) {
		debug(message, t, false);
	}

	public void debug(String message, Throwable t, boolean takeScreenshot) {
		logger.debug(message, t);
	}

	public void fatal(String message) {
		PDFHelper.addText(message);
		fatal(message, false);
		logger.fatal(message);
	}

	public void fatal(String message, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		AllureHelper.newBrokenStep(message, takeScreenshot);
		logger.fatal(message);
	}

	public void fatal(String message, boolean takeScreenshot, boolean assertFail) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		fatal(message, takeScreenshot);

		if (assertFail)
			Assert.fail(message);
	}

	public void fatal(String message, Throwable t) {
		PDFHelper.addText(message);
		fatal(message, t, false);
	}

	public void fatal(String message, Throwable t, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		AllureHelper.newBrokenStep(message, takeScreenshot);
		logger.fatal(message, t);
	}

	public void fatal(String message, Throwable t, boolean takeScreenshot, boolean assertFail) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		fatal(message, takeScreenshot);

		if (assertFail)
			Assert.fail(message, t);
	}

	public void error(String message) {
		PDFHelper.addText(message);
//		error(message, false);
		logger.error(message);
	}

	public void error(String message, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		AllureHelper.newFailStep(message, takeScreenshot);
		logger.error(message);
	}

	public void error(String message, boolean takeScreenshot, boolean assertFail) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		error(message, takeScreenshot);

		if (assertFail)
			Assert.fail(message);
	}

	public void error(String message, Throwable t) {
		PDFHelper.addText(message);
		error(message, t, false);
	}

	public void error(String message, Throwable t, boolean takeScreenshot) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		AllureHelper.newFailStep(message, takeScreenshot);
		logger.error(message, t);
	}

	public void error(String message, Throwable t, boolean takeScreenshot, boolean assertFail) {
		PDFHelper.addTextScreenshot(message, takeScreenshot);
		error(message, t, takeScreenshot);

		if (assertFail)
			Assert.fail(message);
	}

	public void trace(String message) {
		trace(message, false);
	}

	public void trace(String message, boolean takeScreenshot) {
		AllureHelper.newSkippedStep(message, takeScreenshot);
		logger.trace(message);
	}

	public void trace(String message, Throwable t) {
		trace(message, t, false);
	}

	public void trace(String message, Throwable t, boolean takeScreenshot) {
		AllureHelper.newSkippedStep(message, takeScreenshot);
		logger.trace(message, t);
	}

	public void warn(String message) {
		warn(message, false);
	}

	public void warn(String message, boolean takeScreenshot) {
		AllureHelper.newSkippedStep(message, takeScreenshot);
		logger.warn(message);
	}

	public void warn(String message, Throwable t) {
		warn(message, t, false);
	}

	public void warn(String message, Throwable t, boolean takeScreenshot) {
		AllureHelper.newSkippedStep(message, takeScreenshot);
		logger.warn(message, t);
	}
}
