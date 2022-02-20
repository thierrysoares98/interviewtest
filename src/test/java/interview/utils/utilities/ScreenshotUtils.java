package interview.utils.utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import interview.utils.WebController;
import interview.utils.helpers.PDFHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.Reporter;

import interview.utils.exceptions.GenericException;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;

public class ScreenshotUtils {

	protected static String dir;
	protected boolean session = false;
	protected String defaultDir;

	public void createScreenshotDirectory(String testName) {

		File directory = null, dirSession;
		
		if (!session) {
			
			session = true;
			String dia = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime()), 
				   execucao = new SimpleDateFormat("HH_mm_ss").format(Calendar.getInstance().getTime());
			
			if(OSUtils.getOperatingSystemType() == OSUtils.OSType.Windows) {
				dirSession = new File("target"+"\\"+"snapshots"+"\\" + "\\" + dia + "\\" + execucao);
				defaultDir = dirSession.getPath();
			}else {
				dirSession = new File("target/snapshots/"+ "/" + dia + "/" + execucao);
				defaultDir = dirSession.getPath();
			}
				
			dirSession.mkdirs();
		}

		if(OSUtils.getOperatingSystemType() == OSUtils.OSType.Windows) {
			directory = new File(defaultDir + "\\" + testName);
			dir = directory.getPath() + "\\";
		} else {
			directory = new File(defaultDir + "/" + testName);
			dir = directory.getPath() + "/";
		}

		if (directory != null) directory.mkdirs();

	}

	public static String getDir() {
		return dir;
	}

	public static String getNewFileName(String name) {
		return new SimpleDateFormat("HHmmssSSS").format(Calendar.getInstance().getTime()) + "_" + name + ".png";
	}
	

	private boolean takeScreenshotAws(final String name) throws WebDriverException, GenericException {

		try {

			String screenshotDirectory = System.getProperty("appium.screenshots.dir",
															System.getProperty("java.io.tmpdir", ""));
			File screenshot = ((TakesScreenshot) WebController.getInstance().getDriver_()).getScreenshotAs(OutputType.FILE);
			return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));

		} catch (Exception e) {
			Assert.fail("Error at take screenshot.", e);
			return false;
		}
		
	}


	private static File takeScreenshot(String name) throws Exception {

		try {

			File scrFile = ((TakesScreenshot) WebController.getInstance().getDriver_()).getScreenshotAs(OutputType.FILE);
			saveScreenshot_(scrFile, name);

			
			String filePath = scrFile.toString();
			String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
			Reporter.log(path);
			
			return scrFile;

		} catch (Exception e) {
			Assert.fail("Error at take screenshot.", e);
		}
		
		return null;

	}

	public static void saveScreenshot(File file, String name) throws IOException {

		File newFile = new File(getDir() + getNewFileName(name));

		FileUtils.copyFile(file, newFile);
		String filePath = newFile.toString();
		String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";

		Reporter.log(path);

	}
	
	@Attachment(value = "{0}", type="image/png", fileExtension="png")
	public static byte[] takeScreenshotLogger_(final String name) {
		
		byte[] screenshot = null;
		
		try {
			//TO-DO VERIFICAR SE QUER TIRAR FOTO OU NAO
			screenshot = FileUtils.readFileToByteArray(takeScreenshot(name));
		} catch (Exception e) {
			Assert.fail("Error on screenshot.", e);
		}

		return screenshot;
	}

	@Attachment(value = "{0}", type="image/png", fileExtension="png")
	public byte[] takeScreenshot_(final String name) {
		
		byte[] screenshot = null;
		
		try {
			screenshot = FileUtils.readFileToByteArray(takeScreenshot(name));
			PDFHelper.takeScreenshotPDF(name, screenshot);
		} catch (Exception e) {
			Assert.fail("Error on screenshot.", e);
		}

		return screenshot;
	}
	
	public static void saveScreenshot_(File file, String name) throws IOException {
			saveScreenshot(file, name);
	}

	public static byte[] printScreen() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = toolkit.getScreenSize();
		Rectangle limitesTela = new Rectangle(tamanhoTela);

		Robot robot = null;
		byte[] imageInByte = null;
		try {
			robot = new Robot();
			BufferedImage capturaDeTela = robot.createScreenCapture(limitesTela);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( capturaDeTela, "png", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return imageInByte;
	}
}
