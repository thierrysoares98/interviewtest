package interview.utils.functions;

import org.openqa.selenium.WebElement;
import interview.utils.constants.ShouldTypes;

import static org.junit.Assert.*;


public class Should {

    public static void should(WebElement webElement, ShouldTypes attribute, String text){
        String[] aux = attribute.getType().split("\\.");
        switch (aux[0].toLowerCase()){
            case "have":
                assertEquals("Atributo não corresponde com o esperado.",
                        text, Attribute.get(webElement, aux[1]));
                break;
            case "nothave":
                assertNotEquals("Atributo não corresponde com o esperado.",
                        Attribute.get(webElement, aux[1]).equals(text));
                break;
            case "contains":
                assertTrue("Atributo não contém o texto esperado.",
                        Attribute.get(webElement, aux[1]).contains(text));
            case "notcontains":
                assertFalse("Atributo contém o texto não esperado.",
                        Attribute.get(webElement, aux[1]).contains(text));
            default:
                assertFalse("Parametro não esperado no should\nPossiveis parametros: have, notHave, contains, notContains", true);
        }
    }

    public static void should(WebElement webElement, String attribute, String text){
        String[] aux = attribute.split("\\.");
        switch (aux[0].toLowerCase()){
            case "have":
                assertEquals("Atributo não corresponde com o esperado.",
                        text, Attribute.get(webElement, aux[1]));
                break;
            case "nothave":
                assertNotEquals("Atributo não corresponde com o esperado.",
                        Attribute.get(webElement, aux[1]).equals(text));
                break;
            case "contains":
                assertTrue("Atributo não contém o texto esperado.",
                        Attribute.get(webElement, aux[1]).contains(text));
            case "notcontains":
                assertFalse("Atributo contém o texto não esperado.",
                        Attribute.get(webElement, aux[1]).contains(text));
            default:
                assertFalse("Parametro não esperado no should\nPossiveis parametros: have, notHave, contains, notContains", true);
        }
    }

    public static void should(WebElement webElement, String attribute, String text, boolean trim){
        String[] aux = attribute.split("\\.");
        switch (aux[0].toLowerCase()){
            case "have":
                assertEquals("Atributo não corresponde com o esperado.",
                        text, Attribute.get(webElement, aux[1], trim));
                break;
            case "nothave":
                assertNotEquals("Atributo não corresponde com o esperado.",
                        Attribute.get(webElement, aux[1], trim).equals(text));
                break;
            case "contains":
                assertTrue("Atributo não contém o texto esperado.",
                        Attribute.get(webElement, aux[1], trim).contains(text));
            case "notcontains":
                assertFalse("Atributo contém o texto não esperado.",
                        Attribute.get(webElement, aux[1], trim).contains(text));
            default:
                assertFalse("Parametro não esperado no should\nPossiveis parametros: have, notHave, contains, notContains", true);
        }
    }

    public static void should(WebElement webElement, ShouldTypes attribute, String text, boolean trim){
        String[] aux = attribute.getType().split("\\.");
        switch (aux[0].toLowerCase()){
            case "have":
                assertEquals("Atributo não corresponde com o esperado.",
                        text, Attribute.get(webElement, aux[1], trim));
                break;
            case "nothave":
                assertNotEquals("Atributo não corresponde com o esperado.",
                        Attribute.get(webElement, aux[1], trim).equals(text));
                break;
            case "contains":
                assertTrue("Atributo não contém o texto esperado.",
                        Attribute.get(webElement, aux[1], trim).contains(text));
            case "notcontains":
                assertFalse("Atributo contém o texto não esperado.",
                        Attribute.get(webElement, aux[1], trim).contains(text));
            default:
                assertFalse("Parametro não esperado no should\nPossiveis parametros: have, notHave, contains, notContains", true);
        }
    }
}
