package interview.utils.helpers;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import  interview.utils.base.AbstractPageObject;
import   interview.utils.constants.JSAttribute;

public class JSHelper extends AbstractPageObject {
	
	public void execute(String command, WebElement element){
		try {
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript(command, element);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("[ERROR] ERRO AO EXECUTAR O JAVASCRIPTEXECUTOR");
		}
	}
	public void execute(String command){
		try {
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript(command);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("[ERROR] ERRO AO EXECUTAR O JAVASCRIPTEXECUTOR");
		}
	}
	public void prepareJSE(JSAttribute jsattribute, String value, WebElement element) {
		String command = "";
		switch (jsattribute){
		case SET_VALUE:
			command = jsattribute.getAttribute()+value+jsattribute.getSulfix();
			execute(command, element);
			break;

		default:
			break;
		}
	}

}
