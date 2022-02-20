package interview.utils.functions;

import interview.utils.WebController;
import org.openqa.selenium.JavascriptExecutor;

import static org.testng.AssertJUnit.assertFalse;

public class Ajax {

    public void AjaxCallToAlert(String method, String url, String contentType){
        try{
            ((JavascriptExecutor) WebController.getInstance().getDriver_()).executeScript("jQuery.ajax({type: '"+method+"',url: '"+url+"', contentType: '"+contentType+"',success: function success(data) { alert(data) }, error: function error() { console.log('Erro'); } }); ");
        }catch (Exception e){
            assertFalse("Erro ao executar a chamada ajax.\n\n" + e.getStackTrace(), true);
        }
    }
}
