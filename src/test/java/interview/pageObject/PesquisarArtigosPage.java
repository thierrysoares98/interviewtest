package interview.pageObject;

import interview.utils.VirtualElement;
import interview.utils.VirtualElementList;
import interview.utils.WebController;
import interview.utils.base.AbstractPageObject;
import org.junit.Assert;
import org.junit.platform.commons.function.Try;

public class PesquisarArtigosPage extends AbstractPageObject {

    VirtualElement

            btnLupa        =    getElementById("search-open"),
            btnPesquisar   =    getElementByXpath("//div[@class='desktop-search']//input[@class='search-submit']"),
            txtPesquisar   =    getElementByXpath("//div[@class='desktop-search']//input[@class='search-field']"),
            labelResultado =    getElementByXpath("//h1[@class='entry-title']");

    public void acessarHome(String url) {
        WebController.getInstance().getDriver_().navigate().to(url);
    }

    public void clicarBtnLupa() {
        this.btnLupa.locateWebElement(15);
        this.btnLupa.click();
    }

    public void clicarBtnPesquisar() {
        this.btnPesquisar.locateWebElement(15);
        this.btnPesquisar.click();
    }

    public void enviarTextoCampoPesquisar(String texto) {
        this.txtPesquisar.locateWebElement(15);
        this.txtPesquisar.click();
        this.txtPesquisar.sendKeys(texto);
    }

    public void validarArtigos(){
        try {
            VirtualElementList artigos = getListElementsByXpath("//article");
            artigos.forEach(artigo ->  {
                artigo.excuteJStoElement("arguments[0].scrollIntoView();");
                //Acrescente aqui um Thread.sleep(1000); para saber o que está sendo feito em camera lenta.
                Assert.assertTrue(artigo.isDisplayed());
            });
        }catch (Exception e){
            e.fillInStackTrace();
            System.out.println("ERRO AO VALIDAR ARTIGOS");
        }
    }

    public void validarLabelResultado(String resultado){
        try{
            Assert.assertEquals(resultado,labelResultado.getText());
        }catch (Exception e)
        {
            e.fillInStackTrace();
            System.out.println("Erro ao validar Label Resultado");
        }
    }
}
