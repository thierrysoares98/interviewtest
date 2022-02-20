package interview.utils.functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.assertFalse;

public class Attribute {

    public static String get(WebElement webElement, String atribute){
        Select mySelect = null;
        String attribute = null;
        try{
            try{
                mySelect = new Select(webElement);
                //System.out.println("é Um Select");
            }catch (Exception e){
                mySelect = null;
                //System.out.println("Não é Um Select");
            }
            if(mySelect==null){
                 attribute = webElement.getAttribute(atribute);
            }else{
                attribute = mySelect.getFirstSelectedOption().getAttribute(atribute);
            }
            //System.out.println(attribute);
            assertFalse("Atributo não encontrado", attribute.isEmpty());
            return attribute;
        }catch(Exception e){
            assertFalse("Ocorreu um erro ao tentar encontrar o atributo \""+ atribute + "\"\n\n" + e.getStackTrace(), true);
            return null;
        }
    }

    public static String get(WebElement webElement, String atribute, boolean trim){
        Select mySelect = null;
        String attribute = null;
        try{
            try{
                mySelect = new Select(webElement);
                //System.out.println("é Um Select");
            }catch (Exception e){
                mySelect = null;
                //System.out.println("Não é Um Select");
            }
            if(mySelect==null){
                attribute = webElement.getAttribute(atribute);
            }else{
                attribute = mySelect.getFirstSelectedOption().getAttribute(atribute);
            }
            //System.out.println(attribute);
            assertFalse("Atributo não encontrado", attribute.isEmpty());
            return trim ? attribute.trim() : attribute;
        }catch(Exception e){
            assertFalse("Ocorreu um erro ao tentar encontrar o atributo \""+ atribute + "\"\n\n" + e.getStackTrace(), true);
            return null;
        }
    }
}
