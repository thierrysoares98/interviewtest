package interview.utils.helpers;


import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import interview.utils.utilities.ScreenshotUtils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.io.StreamUtil;
import com.itextpdf.text.pdf.*;
import io.qameta.allure.Allure;

public class PDFHelper {

	private static Document doc;
	private static Document docWeb;
	private static String testName;
	private static String genPath;
	private static String genWeb;
	private static String dateCal;
	private static String onlyPath;
	private static String testNamePath;
	private static Boolean textNewPage;
	private static Boolean newPageToText;
	private static String  pdfPath;
	private static List<String> msgTotal = new ArrayList<String>();


	@SuppressWarnings("rawtypes")
	public static String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);



	//@Attachment(value = "teste pdf", type="file/pdf", fileExtension="pdf")
	public static File attachPdfInAllure() throws FileNotFoundException, DocumentException {
		File file = new File(pdfPath);
		InputStream out = new FileInputStream(file);
		Allure.addAttachment(" SA",out);
		return file ;

	}

	public static void initialize(String path, String tName, SimpleDateFormat date) {
		doc = new Document();
		docWeb = new Document(PageSize.A4.rotate());

		dateCal = date.format(new Date());

		textNewPage = true;
		onlyPath = "";

		try {
			testName = tName;
				if (OS.contains("win")) {
					String[] pathSplit = path.split("\\\\");

					for (int i = 0; i < pathSplit.length - 1; i++) {
						onlyPath = onlyPath.concat(pathSplit[i].concat("\\"));
					}

					testNamePath = pathSplit[pathSplit.length - 1];

					PdfWriter.getInstance(docWeb, new FileOutputStream(path + "_old.pdf"));
					genWeb = path + "_old.pdf";
					docWeb.open();
				} else {
					String[] pathSplit = path.split("/");

					for (int i = 0; i < pathSplit.length - 1; i++) {
						onlyPath = onlyPath.concat(pathSplit[i].concat("/"));
					}

					testNamePath = pathSplit[pathSplit.length - 1];

					PdfWriter.getInstance(docWeb, new FileOutputStream(path + "_old.pdf"));
					genWeb = path + "_old.pdf";
					docWeb.open();
				}

		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (DocumentException e) {
			//e.printStackTrace();
		}
	}

	public static void addText(String msg) {

		if (doc == null || docWeb == null)
			return;

		// TO-DO  fazer verificador true ou false para gerar o pdf

				try {
					if (textNewPage) {
						docWeb.newPage();
						Paragraph p = new Paragraph(80, msg);
						p.setAlignment(Element.ALIGN_CENTER);
						docWeb.add(p);
						msgTotal.add("XxTestxX");
						textNewPage = false;
					} else {
						Paragraph p = new Paragraph(80, msg);
						p.setAlignment(Element.ALIGN_CENTER);
						docWeb.add(p);
						msgTotal.add("XxTestxX");
					}
				} catch (DocumentException e) {
					//e.printStackTrace();
				}

		}

	public static void addScreenshot(boolean takeScreenshot) {
		// TO-DO  fazer verificador true ou false para gerar o pdf
		if (doc == null || docWeb == null)
			return;

			try {
					if (takeScreenshot) {
						byte[] img = ScreenshotUtils.takeScreenshotLogger_("");
						Image docImg;

						docImg = Image.getInstance(img);
						docImg.scaleAbsolute(800, 400);

						float xImg = (PageSize.A4.rotate().getWidth() - docImg.getScaledWidth()) / 2;

						docImg.setAbsolutePosition(xImg, 40f);

						doc.newPage();
						docWeb.add(docImg);
						textNewPage = true;
					}
				} catch (BadElementException e) {
					//e.printStackTrace();
				} catch (MalformedURLException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				} catch (DocumentException e) {
					//e.printStackTrace();
				}
			}

	public static void addTextScreenshot(String msg, boolean takeScreenshot) {
		// TO-DO  fazer verificador true ou false para gerar o pdf
		if (doc == null || docWeb == null)
			return;

		try {
					msgTotal.add(msg);

					if (takeScreenshot) {
						byte[] img = ScreenshotUtils.takeScreenshotLogger_(msg);
						Image docImg;

						docImg = Image.getInstance(img);
						docImg.scaleAbsolute(800, 400);

						float xImg = (PageSize.A4.rotate().getWidth() - docImg.getScaledWidth()) / 2;

						docImg.setAbsolutePosition(xImg, 10f);

						docWeb.newPage();
						docWeb.add(docImg);
						textNewPage = true;
					}
				} catch (BadElementException e) {
					//e.printStackTrace();
				} catch (MalformedURLException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				} catch (DocumentException e) {
					//e.printStackTrace();
				}
			}

	public static void addTextScreenshot_(String msg, boolean takeScreenshot) {

		if (doc == null || docWeb == null)
			return;
// TO-DO  fazer verificador true ou false para gerar o pdf
		try {
					msgTotal.add(msg);

					if (takeScreenshot) {
						byte[] img = ScreenshotUtils.takeScreenshotLogger_("");
						Image docImg;

						docImg = Image.getInstance(img);
						docImg.scaleAbsolute(800, 400);

						float xImg = (PageSize.A4.rotate().getWidth() - docImg.getScaledWidth()) / 2;

						docImg.setAbsolutePosition(xImg, 10f);

						docWeb.newPage();
						docWeb.add(docImg);
						textNewPage = true;
					}
				} catch (BadElementException e) {
					//e.printStackTrace();
				} catch (MalformedURLException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				} catch (DocumentException e) {
					//e.printStackTrace();
				}
			}

	public static void takeScreenshotPDF(String name, byte[] screenshot) {
		if (doc == null || docWeb == null)
			return;
		// TO-DO  fazer verificador true ou false para gerar o pdf
		try {
					byte[] img = screenshot;
					Image docImg;

					docImg = Image.getInstance(img);
					docImg.scaleAbsolute(800, 400);

					float xImg = (PageSize.A4.rotate().getWidth() - docImg.getScaledWidth()) / 2;

					docImg.setAbsolutePosition(xImg, 10f);

					docWeb.newPage();
					msgTotal.add(name);
					docWeb.add(docImg);
					textNewPage = true;
				} catch (BadElementException e) {
					//e.printStackTrace();
				} catch (MalformedURLException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				} catch (DocumentException e) {
					//e.printStackTrace();
				}
			}

	public static void headerPdf(Boolean testPassed, List<String> msgs) {

		String status;

		if (testPassed) {
			status = "PASSED_";
		} else {
			status = "FAILED_";
		}
		// TO-DO  fazer verificador true ou false para gerar o pdf
		try {
				PdfReader reader = new PdfReader(genWeb);
				pdfPath = onlyPath + status + testNamePath + ".pdf";
				PdfStamper stamper = new PdfStamper(reader,
						new FileOutputStream(onlyPath + status + testNamePath + ".pdf"));
				BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

				// **********
				msgs.add("");
				msgs.add("");

				for (int i = 1; i <= reader.getNumberOfPages(); i++) {

					PdfContentByte override = stamper.getOverContent(i);

					Image logoInm;
					Image logoCustomer;
					newPageToText = true;

					if (OS.contains("win")) {
						ClassLoader loader = Thread.currentThread().getContextClassLoader();
						InputStream imageInm = loader.getResourceAsStream("pdfReport\\bv.png");

						logoInm = Image.getInstance(StreamUtil.inputStreamToArray(imageInm));
						logoCustomer = Image.getInstance("src\\test\\resources\\pdfReport\\customer.png");
					} else {
						ClassLoader loader = Thread.currentThread().getContextClassLoader();
						InputStream imageInm = loader.getResourceAsStream("pdfReport/bv.png");

						logoInm = Image.getInstance(StreamUtil.inputStreamToArray(imageInm));
						logoCustomer = Image.getInstance("src/test/resources/pdfReport/customer.png");
					}

					logoInm.scaleAbsolute(75, 75);
					logoInm.setAbsolutePosition(0,
							PageSize.A4.rotate().getHeight() - logoInm.getScaledHeight() * 1.0f);

					logoCustomer.scaleAbsolute(75, 25);
					logoCustomer.setAbsolutePosition(
							PageSize.A4.rotate().getWidth() - logoCustomer.getScaledWidth() - 10,
							PageSize.A4.rotate().getHeight() - logoCustomer.getScaledHeight() * 0);

					if (testPassed) {
						Image passed;

						if (OS.contains("win")) {
							ClassLoader loader = Thread.currentThread().getContextClassLoader();
							InputStream imagePassed = loader.getResourceAsStream("pdfReport\\hdGreen.png");

							passed = Image.getInstance(StreamUtil.inputStreamToArray(imagePassed));
						} else {
							ClassLoader loader = Thread.currentThread().getContextClassLoader();
							InputStream imagePassed = loader.getResourceAsStream("pdfReport/hdGreen.png");

							passed = Image.getInstance(StreamUtil.inputStreamToArray(imagePassed));
						}

						passed.scaleAbsolute(1400, 75);
						passed.setAbsolutePosition(0, PageSize.A4.rotate().getHeight() - passed.getScaledHeight());
						override.addImage(passed);
					} else {
						Image failed;

						if (OS.contains("win")) {
							ClassLoader loader = Thread.currentThread().getContextClassLoader();
							InputStream imageFailed = loader.getResourceAsStream("pdfReport\\hdRed.png");

							failed = Image.getInstance(StreamUtil.inputStreamToArray(imageFailed));
						} else {
							ClassLoader loader = Thread.currentThread().getContextClassLoader();
							InputStream imageFailed = loader.getResourceAsStream("pdfReport/hdRed.png");

							failed = Image.getInstance(StreamUtil.inputStreamToArray(imageFailed));
						}

						failed.scaleAbsolute(1400, 75);
						failed.setAbsolutePosition(0, PageSize.A4.rotate().getHeight() - failed.getScaledHeight());
						override.addImage(failed);
					}

					override.beginText();
					override.setFontAndSize(bf, 14);

					// Date
					override.showTextAlignedKerned(Element.ALIGN_CENTER, dateCal, PageSize.A4.rotate().getWidth() - 90,
							PageSize.A4.rotate().getHeight() - logoInm.getScaledHeight() - 40, 0f);
					override.endText();

					override.addImage(logoInm);
					override.addImage(logoCustomer);

					// TestName
					Rectangle testNameRect = new Rectangle(165, 530, logoCustomer.getAbsoluteX() + 3, 580);
					testNameRect.setBorder(Rectangle.BOX);
					override.rectangle(testNameRect);

					ColumnText ctTest = new ColumnText(override);
					ctTest.setSimpleColumn(testNameRect);
					Font courierFontTN = new Font(Font.FontFamily.COURIER, 14);
					Paragraph pTestName = new Paragraph(testName, courierFontTN);
					pTestName.setAlignment(Element.ALIGN_CENTER);
					ctTest.addElement(pTestName);
					ctTest.go();

					// LogInfo
					Rectangle rect = new Rectangle(20, 420, 820, 500);
					rect.setBorder(Rectangle.BOX);
					override.rectangle(rect);

					ColumnText ct = new ColumnText(override);
					ct.setSimpleColumn(rect);
					Font catFont = new Font(Font.FontFamily.COURIER, 12);

					while (i - i < msgs.size() && msgs.get(i - i).equals("XxTestxX")
							|| i - i < msgs.size() && msgs.get(i - i).equals("Exception on scenario execution")) {
						msgs.remove(i - i);

						if (newPageToText) {
							override = stamper.getOverContent(i + 1);
							newPageToText = false;
						}
					}

					if (i - i < msgs.size() && newPageToText) {
						Paragraph p = new Paragraph(msgs.get(i - i), catFont);
						p.setAlignment(Element.ALIGN_CENTER);
						ct.addElement(p);
						ct.go();
						msgs.remove(i - i);
					}
				}
				stamper.close();
				File file = new File(genWeb);
				if (file.exists()) {
					file.delete();
				}
				msgs.clear();

			} catch (BadElementException e) {
				//e.printStackTrace();
			} catch (MalformedURLException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			} catch (DocumentException e) {
				//e.printStackTrace();
			}
		}

	public static void terminate(boolean testResult) throws IOException {

			if (docWeb == null) 
				return;
			try {
				docWeb.close();
				PdfReader reader = new PdfReader(genWeb);
				
				if(reader.getNumberOfPages() == 0) {
					docWeb = null;
					
					File file = new File(genPath);
					if (file.exists()) {
						file.delete();
					}
				} else {
					docWeb.close();
	 				headerPdf(testResult, msgTotal);
					docWeb = null;
				}
			} catch (Exception e) {
				
			}
		}
	}
