package interview.utils.functions;

import interview.utils.WebController;

import java.util.Set;

import static org.testng.AssertJUnit.assertFalse;

public class SwitchTo {

    public void switchToWindowNumber(int number){
        try{
            Object[] windows = WebController.getInstance().getDriver_().getWindowHandles().toArray();
            assertFalse("O Numero requerido é maior que o numero de telas", windows.length< number);
            assertFalse("O Numero não pode ser menor que 0", number<0);
            WebController.getInstance().getDriver_().switchTo().window(windows[number-1].toString());
        }catch (Exception e){
            assertFalse("Ocorreu um erro ao alterar o driver para a tela de numero " + number, true);
        }
    }

    public void switchToWindownWithUrlContains(String urlPart){
        try{
            String actualWindow = WebController.getInstance().getDriver_().getWindowHandle();
            Set<String> windows = WebController.getInstance().getDriver_().getWindowHandles();
            for(String window : windows){
                if(!actualWindow.equals(window))
                    if(WebController.getInstance().getDriver_().switchTo().window(window).getCurrentUrl().contains(urlPart))
                        return;
            }
            assertFalse("Tela com url contendo " + urlPart + " não encontrada.", true);
        }catch (Exception e){
            assertFalse("Ocorreu um erro ao alterar o driver para a tela que a url Contem " + urlPart + "\n\n" + e.getStackTrace(), true);
        }
    }

    public void switchToFrameByIndex(int index){
        try{
            WebController.getInstance().getDriver_().switchTo().frame(index);
        }catch (Exception e){
            assertFalse("Ocorreu um erro ao alterar para o frame de index " + index + "\n\n" + e.getStackTrace(), true);
        }
    }

    public void switchToFrameWithId(String id){
        try{
            WebController.getInstance().getDriver_().switchTo().frame(id);
        }catch (Exception e){
            assertFalse("Ocorreu um erro ao alterar para o frame de id " + id + "\n\n" + e.getStackTrace(), true);
        }
    }

    public void switchToParentFrame(){
        try{
            WebController.getInstance().getDriver_().switchTo().parentFrame();
        }catch (Exception e){
            assertFalse("Ocorreu um erro ao alterar para o parent frame\n\n" + e.getStackTrace(), true);
        }
    }
}
